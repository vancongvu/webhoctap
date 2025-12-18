(function() {
    // Cau hinh mac dinh cho chatbot
    const defaultConfig = {
        title: 'Chat',
        apiKey: '', // de API key o day hoac trong window.CauHinhChat
        position: { bottom: 24, right: 24 },
        primaryColor: "#111827",
        soLuongKyTuToiDa: 1000
    }

    // Gop config mac dinh voi config nguoi dung
    const config = { ...defaultConfig, ...(window.CauHinhChat || {})};
    config.position = { ...defaultConfig.position, ...(config.position || {}) };

    // Key de luu data vao localStorage
    const key = 'chatbot_hoithoai';

    // Trang thai cua chatbot
    let state = {
        dangMo: false,
        tinnhan: [],
        dangSuyNghi: false
    }

    // Load trang thai tu localStorage
    function loadTrangThai() {
        try {
            const luuTru = localStorage.getItem(key);
            if (luuTru) {
                const duLieu = JSON.parse(luuTru);
                state.dangMo = duLieu.dangMo || false;
                state.tinnhan = duLieu.tinnhan || [];
            }
        } catch (error) {
            console.log('Loi khi tai trang thai:', error);
        }
    }

    // Luu trang thai vao localStorage
    function luuTrangThai() {
        try {
            localStorage.setItem(key, JSON.stringify({
                dangMo: state.dangMo,
                tinnhan: state.tinnhan
            }));
        } catch (error) {
            console.log('Loi khi luu trang thai:', error);
        }
    }

    // Mo hoac dong khung chat
    function DongMoChat() {
        state.dangMo = !state.dangMo;
        const khungChat = document.getElementById('chat-widget-panel');

        if (state.dangMo) {
            khungChat.classList.remove('chat-widget-hidden');
            // Focus vao o input khi mo chat
            const input = document.getElementById('chat-widget-input');
            setTimeout(() => input.focus(), 100);
        } else {
            khungChat.classList.add('chat-widget-hidden');
        }

        luuTrangThai();
    }

    // Dong khung chat
    function DongChat() {
        state.dangMo = false;
        const khungChat = document.getElementById('chat-widget-panel');
        khungChat.classList.add('chat-widget-hidden');
        luuTrangThai();
    }

    // Gui tin nhan di
    function GuiTinNhan() {
        const input = document.getElementById('chat-widget-input');
        const tinNhan = input.value.trim();

        // Khong gui neu rong hoac dang cho AI tra loi
        if (!tinNhan || state.dangSuyNghi) {
            return;
        }

        // Xoa noi dung input
        input.value = '';
        input.style.height = 'auto';

        // Goi AI
        goiAI(tinNhan);
    }

    // Hien thi tat ca tin nhan
    function hienThiTinNhan() {
        const khungTinNhan = document.getElementById('chat-widget-messages');

        // Hien thi loi chao neu chua co tin nhan
        if (state.tinnhan.length === 0 && !state.dangSuyNghi) {
            khungTinNhan.innerHTML = `
                <div class="chat-widget-empty-state">
                    <div class="chat-widget-empty-state-icon">👋</div>
                    <div class="chat-widget-empty-state-text">
                        Xin chào! Tôi có thể giúp gì cho bạn?
                    </div>
                </div>
            `;
            return;
        }

        // Xoa het tin nhan cu
        khungTinNhan.innerHTML = '';

        // Hien thi tung tin nhan
        state.tinnhan.forEach(msg => {
            const tinNhanDiv = document.createElement('div');
            tinNhanDiv.className = `chat-widget-message ${msg.role}`;

            const noiDung = document.createElement('div');
            noiDung.className = 'chat-widget-message-bubble';
            noiDung.textContent = msg.content;

            tinNhanDiv.appendChild(noiDung);
            khungTinNhan.appendChild(tinNhanDiv);
        });

        // Hien thi dau ... khi AI dang suy nghi
        if (state.dangSuyNghi) {
            const dangGo = document.createElement('div');
            dangGo.className = 'chat-widget-message assistant';
            dangGo.innerHTML = `
                <div class="chat-widget-typing">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            `;
            khungTinNhan.appendChild(dangGo);
        }

        // Tu dong cuon xuong tin nhan moi nhat
        khungTinNhan.scrollTop = khungTinNhan.scrollHeight;
    }

    // Them tin nhan moi vao danh sach
    function themTinNhan(role, content) {
        state.tinnhan.push({
            role: role,
            content: content,
            timestamp: Date.now()
        });

        luuTrangThai();
        hienThiTinNhan();
    }

    // Hien thi loi cho nguoi dung
    function hienThiLoi(thongBao) {
        state.tinnhan.push({
            role: 'error',
            content: '⚠️ ' + thongBao,
            timestamp: Date.now()
        });

        luuTrangThai();
        hienThiTinNhan();
    }

    // Goi AI de tra loi
    async function goiAI(tinNhan) {
        // Kiem tra API key
        if (!config.apiKey) {
            themTinNhan('user', tinNhan);
            state.dangSuyNghi = false;
            hienThiLoi('Chưa có API key! Vui lòng thêm API key vào config.');
            return;
        }

        // Them tin nhan nguoi dung
        themTinNhan('user', tinNhan);

        // Bat dau trang thai dang suy nghi
        state.dangSuyNghi = true;
        hienThiTinNhan();

        try {
            // Chuan bi lich su hoi thoai
            const lichSu = state.tinnhan
                .filter(tin => tin.role === 'user' || tin.role === 'assistant')
                .slice(-20) // Chi lay 20 tin nhan gan nhat
                .map(tin => ({
                    role: tin.role === 'user' ? 'user' : 'model',
                    parts: [{ text: tin.content }]
                }));

            // Goi Gemini API
            const response = await fetch(
                `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=${config.apiKey}`,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        contents: lichSu
                    })
                }
            );

            // Kiem tra loi tu API
            if (!response.ok) {
                const loiData = await response.json().catch(() => ({}));
                throw new Error(loiData.error?.message || 'Lỗi kết nối API');
            }

            const data = await response.json();
            const traLoi = data.candidates?.[0]?.content?.parts?.[0]?.text;

            if (!traLoi) {
                throw new Error('Không nhận được phản hồi từ AI');
            }

            // Them tin nhan tra loi tu AI
            state.dangSuyNghi = false;
            themTinNhan('assistant', traLoi);

        } catch (error) {
            console.log('Loi khi goi AI:', error);
            state.dangSuyNghi = false;
            hienThiLoi(error.message || 'Có lỗi xảy ra. Vui lòng thử lại!');
        }
    }

    // Khoi tao chatbot
    function khoiTao() {
        loadTrangThai();
        chenCss();
        chenHtml();
        hienThiTinNhan();

        // Hien thi lai trang thai mo/dong
        if (state.dangMo) {
            document.getElementById('chat-widget-panel').classList.remove('chat-widget-hidden');
        }

        // Gan cac su kien
        document.getElementById('chat-widget-toggle').addEventListener('click', DongMoChat);
        document.getElementById('chat-widget-close').addEventListener('click', DongChat);
        document.getElementById('chat-widget-send').addEventListener('click', GuiTinNhan);

        // Xu ly phim Enter de gui tin nhan
        const input = document.getElementById('chat-widget-input');

        input.addEventListener('keydown', function(e) {
            // Nhan Enter (khong Shift) de gui
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                GuiTinNhan();
            }
        });

        // Tu dong tang kich thuoc textarea khi go
        input.addEventListener('input', function() {
            this.style.height = 'auto';
            this.style.height = Math.min(this.scrollHeight, 120) + 'px';
        });

        // Cap nhat trang thai nut Send
        const capNhatNutSend = function() {
            const nutSend = document.getElementById('chat-widget-send');
            const coNoiDung = input.value.trim().length > 0;
            nutSend.disabled = state.dangSuyNghi || !coNoiDung;
        };

        input.addEventListener('input', capNhatNutSend);
        setInterval(capNhatNutSend, 100);
    }

    // Chen HTML vao trang
    function chenHtml() {
        const khungChinh = document.createElement('div');
        khungChinh.className = 'chat-widget-container';
        khungChinh.innerHTML = `
            <button class="chat-widget-button" id="chat-widget-toggle" aria-label="Mo chat">
                💬
            </button>
            <div class="chat-widget-panel chat-widget-hidden" id="chat-widget-panel">
                <div class="chat-widget-header">
                    <div class="chat-widget-header-title">
                        <span class="chat-widget-status"></span>
                        <span>${config.title}</span>
                    </div>
                    <div class="chat-widget-header-actions">
                        <button class="chat-widget-icon-btn" id="chat-widget-close" title="Dong">
                            ✕
                        </button>
                    </div>
                </div>
                <div class="chat-widget-messages" id="chat-widget-messages"></div>
                <div class="chat-widget-input-area">
                    <textarea
                        class="chat-widget-textarea"
                        id="chat-widget-input"
                        placeholder="Nhập tin nhắn..."
                        rows="1"
                        maxlength="${config.soLuongKyTuToiDa}"
                    ></textarea>
                    <button class="chat-widget-send-btn" id="chat-widget-send">Gửi</button>
                </div>
            </div>
        `;
        document.body.appendChild(khungChinh);
    }

    // Chen CSS vao trang
    function chenCss() {
        const style = document.createElement('style');
        style.textContent = `
      .chat-widget-container {
        position: fixed;
        bottom: ${config.position.bottom}px;
        right: ${config.position.right}px;
        z-index: 999999;
        font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      }

      .chat-widget-button {
        width: 56px;
        height: 56px;
        border-radius: 50%;
        background: ${config.primaryColor};
        color: white;
        border: none;
        cursor: pointer;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
        display: flex;
        align-items: center;
        justify-content: center;
        transition: transform 0.2s, box-shadow 0.2s;
        font-size: 24px;
      }

      .chat-widget-button:hover {
        transform: scale(1.05);
        box-shadow: 0 6px 16px rgba(0, 0, 0, 0.2);
      }

      .chat-widget-button:active {
        transform: scale(0.95);
      }

      .chat-widget-panel {
        position: absolute;
        bottom: 70px;
        right: 0;
        width: 360px;
        max-width: 90vw;
        max-height: 70vh;
        background: white;
        border-radius: 12px;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.12);
        display: flex;
        flex-direction: column;
        overflow: hidden;
        transform-origin: bottom right;
        animation: chatPanelOpen 0.2s ease-out;
      }

      @keyframes chatPanelOpen {
        from {
          opacity: 0;
          transform: scale(0.9) translateY(10px);
        }
        to {
          opacity: 1;
          transform: scale(1) translateY(0);
        }
      }

      .chat-widget-hidden {
        display: none !important;
      }

      .chat-widget-header {
        padding: 16px;
        background: ${config.primaryColor};
        color: white;
        display: flex;
        align-items: center;
        justify-content: space-between;
        border-radius: 12px 12px 0 0;
      }

      .chat-widget-header-title {
        font-weight: 600;
        font-size: 16px;
        display: flex;
        align-items: center;
        gap: 8px;
      }

      .chat-widget-status {
        width: 8px;
        height: 8px;
        border-radius: 50%;
        background: #10b981;
        display: inline-block;
      }

      .chat-widget-header-actions {
        display: flex;
        gap: 8px;
      }

      .chat-widget-icon-btn {
        background: none;
        border: none;
        color: white;
        cursor: pointer;
        padding: 4px;
        border-radius: 4px;
        display: flex;
        align-items: center;
        justify-content: center;
        transition: background 0.2s;
        font-size: 18px;
      }

      .chat-widget-icon-btn:hover {
        background: rgba(255, 255, 255, 0.1);
      }

      .chat-widget-messages {
        flex: 1;
        overflow-y: auto;
        padding: 16px;
        display: flex;
        flex-direction: column;
        gap: 12px;
        background: #f9fafb;
      }

      .chat-widget-message {
        display: flex;
        gap: 8px;
        animation: messageSlideIn 0.3s ease-out;
      }

      @keyframes messageSlideIn {
        from {
          opacity: 0;
          transform: translateY(10px);
        }
        to {
          opacity: 1;
          transform: translateY(0);
        }
      }

      .chat-widget-message.user {
        flex-direction: row-reverse;
      }

      .chat-widget-message-bubble {
        max-width: 75%;
        padding: 10px 14px;
        border-radius: 12px;
        word-wrap: break-word;
        white-space: pre-wrap;
        line-height: 1.4;
        font-size: 14px;
      }

      .chat-widget-message.user .chat-widget-message-bubble {
        background: ${config.primaryColor};
        color: white;
        border-bottom-right-radius: 4px;
      }

      .chat-widget-message.assistant .chat-widget-message-bubble {
        background: white;
        color: #1f2937;
        border: 1px solid #e5e7eb;
        border-bottom-left-radius: 4px;
      }

      .chat-widget-message.error .chat-widget-message-bubble {
        background: #fef2f2;
        color: #991b1b;
        border: 1px solid #fecaca;
      }

      .chat-widget-typing {
        display: flex;
        gap: 4px;
        padding: 10px 14px;
        background: white;
        border-radius: 12px;
        border: 1px solid #e5e7eb;
        width: fit-content;
      }

      .chat-widget-typing span {
        width: 8px;
        height: 8px;
        border-radius: 50%;
        background: #9ca3af;
        animation: typingDot 1.4s infinite;
      }

      .chat-widget-typing span:nth-child(2) {
        animation-delay: 0.2s;
      }

      .chat-widget-typing span:nth-child(3) {
        animation-delay: 0.4s;
      }

      @keyframes typingDot {
        0%, 60%, 100% {
          transform: translateY(0);
          opacity: 0.7;
        }
        30% {
          transform: translateY(-10px);
          opacity: 1;
        }
      }

      .chat-widget-input-area {
        padding: 12px 16px;
        background: white;
        border-top: 1px solid #e5e7eb;
        display: flex;
        gap: 8px;
      }

      .chat-widget-textarea {
        flex: 1;
        border: 1px solid #d1d5db;
        border-radius: 8px;
        padding: 8px 12px;
        font-size: 14px;
        font-family: inherit;
        resize: none;
        max-height: 120px;
        transition: border-color 0.2s;
      }

      .chat-widget-textarea:focus {
        outline: none;
        border-color: ${config.primaryColor};
      }

      .chat-widget-send-btn {
        background: ${config.primaryColor};
        color: white;
        border: none;
        border-radius: 8px;
        padding: 8px 16px;
        cursor: pointer;
        font-weight: 500;
        font-size: 14px;
        transition: opacity 0.2s;
      }

      .chat-widget-send-btn:hover:not(:disabled) {
        opacity: 0.9;
      }

      .chat-widget-send-btn:disabled {
        opacity: 0.5;
        cursor: not-allowed;
      }

      .chat-widget-modal {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        display: flex;
        align-items: center;
        justify-content: center;
        z-index: 1000000;
        animation: fadeIn 0.2s;
      }

      @keyframes fadeIn {
        from { opacity: 0; }
        to { opacity: 1; }
      }

      .chat-widget-modal-content {
        background: white;
        border-radius: 12px;
        padding: 24px;
        max-width: 400px;
        width: 90%;
        box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);
        animation: modalSlideIn 0.3s ease-out;
      }

      @keyframes modalSlideIn {
        from {
          opacity: 0;
          transform: scale(0.9) translateY(-20px);
        }
        to {
          opacity: 1;
          transform: scale(1) translateY(0);
        }
      }

      .chat-widget-modal-header {
        font-size: 18px;
        font-weight: 600;
        margin-bottom: 16px;
        color: #1f2937;
      }

      .chat-widget-modal-body {
        margin-bottom: 20px;
      }

      .chat-widget-modal-label {
        display: block;
        font-size: 14px;
        font-weight: 500;
        margin-bottom: 8px;
        color: #374151;
      }

      .chat-widget-modal-input {
        width: 100%;
        padding: 8px 12px;
        border: 1px solid #d1d5db;
        border-radius: 6px;
        font-size: 14px;
        font-family: monospace;
        box-sizing: border-box;
      }

      .chat-widget-modal-input:focus {
        outline: none;
        border-color: ${config.primaryColor};
      }

      .chat-widget-modal-footer {
        display: flex;
        gap: 8px;
        justify-content: flex-end;
      }

      .chat-widget-modal-btn {
        padding: 8px 16px;
        border-radius: 6px;
        font-size: 14px;
        font-weight: 500;
        cursor: pointer;
        transition: opacity 0.2s;
        border: none;
      }

      .chat-widget-modal-btn:hover {
        opacity: 0.9;
      }

      .chat-widget-modal-btn.primary {
        background: ${config.primaryColor};
        color: white;
      }

      .chat-widget-modal-btn.secondary {
        background: #f3f4f6;
        color: #374151;
      }

      .chat-widget-empty-state {
        text-align: center;
        padding: 40px 20px;
        color: #6b7280;
      }

      .chat-widget-empty-state-icon {
        font-size: 48px;
        margin-bottom: 12px;
      }

      .chat-widget-empty-state-text {
        font-size: 14px;
        line-height: 1.5;
      }

      @media (max-width: 480px) {
        .chat-widget-panel {
          width: calc(100vw - 32px);
          max-height: 80vh;
        }
      }
        `;
        document.head.appendChild(style);
    }

    // Bat dau khoi tao khi trang da san sang
    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', khoiTao);
    } else {
        khoiTao();
    }

})();