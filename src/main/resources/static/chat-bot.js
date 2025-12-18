(function() {
    // thiet dat AI
    
    const defaultConfig = {
        title: 'Chat',
        model: '',
        position: { bottom: 24, right: 24 },
        primaryColor: "#111827",
        soLuongKyTuToiDa: 1000
    }

    // gop config mac dinh voi config nguoi dung
    const config = { ...defaultConfig, ...(window.CauHinhChat || {})};

    // Key de luu data vao localStorage
    const key = 'chatbot_hoithoai';
    const apiKeyStorage = 'chatbot_api_key';

    // trang thai
    let state = {
        dangMo: false,
        tinnhan: [],
        dangSuyNghi: false,
        apiKey: ''
    }

    // load trang thai
    function loadTrangThai() {
        // doc tu local storage
        try {
            const chatbot = localStorage.getItem(key);
            if (chatbot) {
                // lay trang thai ra
                const parsed = JSON.parse(chatbot);
                state.dangMo = parsed.dangMo || false;
                state.tinnhan = parsed.tinnhan || [];
            }

            state.apiKey = localStorage.getItem(apiKeyStorage) || '';
        } catch (error) {
            console.log('Loi khi tai trang thai', error);
        }
    }

    // luu trang thai
    function luuTrangThai() {
        try {
            // luu trang thai tin nhan va trang thai dong/mo
            localStorage.setItem(key, JSON.stringify({
                dangMo: state.dangMo,
                tinnhan: state.tinnhan
            }))
            // luu api key
            localStorage.setItem(apiKeyStorage, state.apiKey)
        } catch (error) {
            console.log('Loi khi luu trang thai', error);
        }
    }

    function DongMoChat() {
        // dao nguoc trang thai hien tai
        state.dangMo = !state.dangMo;
        const khungChat = document.getElementById('chat-widget-panel');

        if (state.dangMo) {
            // bo class an khung chat
            khungChat.classList.remove('chat-widget-hidden');
        } else {
            khungChat.classList.add('chat-widget-hidden');
        }

        luuTrangThai();
    }

    function DongChat() {
        state.dangMo = false;
        const khungChat = document.getElementById('chat-widget-panel');
        khungChat.classList.add('chat-widget-hidden');

        luuTrangThai();
    }

    function GuiTinNhan() {
        const input = document.getElementById('chat-widget-input');
        // lay tin nhan ra
        const tinnhan = input.value;

        console.log('tin nhan', tinnhan);

        if (!tinnhan) {
            return;
        }

        goiAI(tinnhan);
        // xoa chat
        input.value = '';
    }

    function hienThiTinNhan() {
        const messagesContainer = document.getElementById('chat-widget-messages');

        if (state.tinnhan.length === 0 && !state.dangSuyNghi) {
            messagesContainer.innerHTML = `
                <div class="chat-widget-empty-state">
                <div class="chat-widget-empty-state-icon">👋</div>
                <div class="chat-widget-empty-state-text">
                    Welcome! How can I help you today?<br>
                    ${!state.apiKey ? '<br><em>Please set your API key in Settings to start chatting.</em>' : ''}
                </div>
                </div>
            `;
            return;
        }

        messagesContainer.innerHTML = '';

        state.tinnhan.forEach(msg => {
        const messageDiv = document.createElement('div');
            messageDiv.className = `chat-widget-message ${msg.role}`;

            const bubble = document.createElement('div');
            bubble.className = 'chat-widget-message-bubble';
            bubble.textContent = msg.content;

            messageDiv.appendChild(bubble);
            messagesContainer.appendChild(messageDiv);
        });

        if (state.dangSuyNghi) {
            const typingDiv = document.createElement('div');
            typingDiv.className = 'chat-widget-message assistant';
            typingDiv.innerHTML = `
                <div class="chat-widget-typing">
                <span></span>
                <span></span>
                <span></span>
                </div>
            `;
            messagesContainer.appendChild(typingDiv);
        }

        // Auto-scroll to bottom
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
    }

    function themTinNhan(role, content) {
        state.tinnhan.push({
            role,
            content,
            timestamp: Date.now()
        });

        luuTrangThai();
        hienThiTinNhan();
    }

    async function goiAI(tinnhan) {
        const apiKey = '';
        // them tin nhan nguoi dung cho ai
        themTinNhan('user', tinnhan);
        // show trang thai dang suy nghi
        state.dangSuyNghi = true;

        hienThiTinNhan();

        // goi ai
        try {
            const noidung = state.tinnhan
            .filter(tin => tin.role === 'user' || tin.role === 'assistant')
            .map(tin => ({
                role: tin.role === 'user' ? 'user' : 'model',
                parts: [{
                    text: tin.content
                }]
            }))

            console.log('noidung', noidung);

            // goi API
            const response = await fetch(
                `https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=${apiKey}`,
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        contents: noidung
                    })
                }
            );

            if (!response.ok) {
                console.log('loi xay ra');
            }

            const data = await response.json();
            const tinNhanPhanHoi = data.candidates?.[0]?.content?.parts?.[0]?.text;

            if (!tinNhanPhanHoi) {
                throw new Error('Loi API');
            }

            // Them tin nhan phan hoi
            state.dangSuyNghi = false;
            themTinNhan('assistant', tinNhanPhanHoi);
        } catch (error) {
            
        }
    }

    function khoiTao() {
        loadTrangThai();
        chenCss();
        chenHtml();

        // gan su kien
        document.getElementById('chat-widget-toggle').addEventListener('click', DongMoChat);
        document.getElementById('chat-widget-close').addEventListener('click', DongChat);
        document.getElementById('chat-widget-send').addEventListener('click', GuiTinNhan);
    }

    function chenHtml() {
        const container = document.createElement('div');
        container.className = 'chat-widget-container';
        container.innerHTML = `
        <button class="chat-widget-button" id="chat-widget-toggle" aria-label="Toggle chat">
            💬
        </button>
        <div class="chat-widget-panel chat-widget-hidden" id="chat-widget-panel">
            <div class="chat-widget-header">
            <div class="chat-widget-header-title">
                <span class="chat-widget-status"></span>
                <span>${config.title}</span>
            </div>
            <div class="chat-widget-header-actions">
                <button class="chat-widget-icon-btn" id="chat-widget-close" title="Close">
                ✕
                </button>
            </div>
            </div>
            <div class="chat-widget-messages" id="chat-widget-messages"></div>
            <div class="chat-widget-input-area">
            <textarea
                class="chat-widget-textarea"
                id="chat-widget-input"
                placeholder="Type a message..."
                rows="1"
                maxlength="${config.soLuongKyTuToiDa}"
            ></textarea>
            <button class="chat-widget-send-btn" id="chat-widget-send">Send</button>
            </div>
        </div>
        `;
        document.body.appendChild(container);
    }

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

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', khoiTao);
    } else {
        khoiTao();
    }
    
})();