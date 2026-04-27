// KiddoCode 儿童庆祝系统
class KiddoCelebration {
    constructor() {
        this.soundEnabled = true;
        this.audioContext = null;
    }

    // 初始化音频上下文
    initAudio() {
        try {
            this.audioContext = new (window.AudioContext || window.webkitAudioContext)();
        } catch (e) {
            console.log('音频不支持');
        }
    }

    // 播放简单音效
    playSound(type) {
        if (!this.soundEnabled || !this.audioContext) return;

        const oscillator = this.audioContext.createOscillator();
        const gainNode = this.audioContext.createGain();
        
        oscillator.connect(gainNode);
        gainNode.connect(this.audioContext.destination);

        switch(type) {
            case 'win':
                oscillator.frequency.setValueAtTime(523.25, this.audioContext.currentTime);
                oscillator.frequency.setValueAtTime(659.25, this.audioContext.currentTime + 0.1);
                oscillator.frequency.setValueAtTime(783.99, this.audioContext.currentTime + 0.2);
                oscillator.frequency.setValueAtTime(1046.50, this.audioContext.currentTime + 0.3);
                gainNode.gain.setValueAtTime(0.3, this.audioContext.currentTime);
                gainNode.gain.exponentialRampToValueAtTime(0.01, this.audioContext.currentTime + 0.8);
                oscillator.start();
                oscillator.stop(this.audioContext.currentTime + 0.8);
                break;
            case 'collect':
                oscillator.frequency.setValueAtTime(880, this.audioContext.currentTime);
                gainNode.gain.setValueAtTime(0.2, this.audioContext.currentTime);
                gainNode.gain.exponentialRampToValueAtTime(0.01, this.audioContext.currentTime + 0.2);
                oscillator.start();
                oscillator.stop(this.audioContext.currentTime + 0.2);
                break;
            case 'star':
                oscillator.frequency.setValueAtTime(660, this.audioContext.currentTime);
                oscillator.frequency.setValueAtTime(880, this.audioContext.currentTime + 0.1);
                gainNode.gain.setValueAtTime(0.25, this.audioContext.currentTime);
                gainNode.gain.exponentialRampToValueAtTime(0.01, this.audioContext.currentTime + 0.4);
                oscillator.start();
                oscillator.stop(this.audioContext.currentTime + 0.4);
                break;
        }
    }

    // 创建五彩纸屑
    createConfetti() {
        const colors = ['#ff6b6b', '#feca57', '#48dbfb', '#ff9ff3', '#1dd1a1', '#f368e0', '#ff9f43'];
        const shapes = ['⬤', '■', '▲', '♦', '★'];
        
        for (let i = 0; i < 60; i++) {
            const confetti = document.createElement('div');
            confetti.className = 'confetti-piece';
            confetti.innerHTML = shapes[Math.floor(Math.random() * shapes.length)];
            confetti.style.left = Math.random() * 100 + 'vw';
            confetti.style.color = colors[Math.floor(Math.random() * colors.length)];
            confetti.style.animationDuration = (2 + Math.random() * 3) + 's';
            confetti.style.animationDelay = Math.random() * 0.5 + 's';
            confetti.style.fontSize = (10 + Math.random() * 15) + 'px';
            document.body.appendChild(confetti);
            
            setTimeout(() => confetti.remove(), 5000);
        }
    }

    // 创建星星雨
    createStarRain() {
        const stars = ['⭐', '🌟', '✨', '💫', '⭐'];
        
        for (let i = 0; i < 25; i++) {
            setTimeout(() => {
                const star = document.createElement('div');
                star.className = 'star-drop';
                star.innerHTML = stars[Math.floor(Math.random() * stars.length)];
                star.style.left = Math.random() * 100 + 'vw';
                star.style.animationDuration = (1.5 + Math.random() * 1.5) + 's';
                star.style.animationDelay = Math.random() * 0.3 + 's';
                document.body.appendChild(star);
                
                setTimeout(() => star.remove(), 3500);
            }, i * 50);
        }
    }

    // 增强的胜利弹窗
    showEnhancedWin(gameName, levelName, stars, stats, callbacks) {
        // 初始化音频
        if (!this.audioContext) this.initAudio();

        // 创建弹窗
        const modal = document.createElement('div');
        modal.className = 'win-modal-enhanced';
        modal.id = 'kiddo-win-modal';
        
        const characterEmojis = ['🎉', '🎊', '🏆', '🌟', '🎈'];
        const character = characterEmojis[Math.floor(Math.random() * characterEmojis.length)];

        let statsHTML = '';
        for (let key in stats) {
            statsHTML += `<p style="margin:8px 0;color:#2d3436;font-size:1.1rem;">
                <strong>${key}:</strong> <span style="color:#e74c3c;">${stats[key]}</span>
            </p>`;
        }

        modal.innerHTML = `
            <div class="win-content-enhanced">
                <div style="font-size:80px;animation:celebrate-character 0.8s ease-in-out infinite;">
                    ${character}
                </div>
                <h2 class="celebrate-title" style="font-size:2.5rem;color:#2d3436;margin:15px 0;">
                    太棒了！
                </h2>
                <p style="font-size:1.3rem;color:#636e72;margin-bottom:20px;">
                    ${gameName} - ${levelName}
                </p>
                
                <div style="margin:25px 0;">
                    <div style="font-size:50px;display:flex;justify-content:center;gap:15px;">
                        ${[1,2,3].map(i => `<span style="opacity:${i <= stars ? '1' : '0.3'};animation:${i <= stars ? 'starPop 0.5s ease ' + (i * 0.2) + 's both' : 'none'}">⭐</span>`).join('')}
                    </div>
                </div>
                
                <div style="margin:20px 0;">
                    ${statsHTML}
                </div>
                
                <div style="display:flex;gap:10px;flex-wrap:wrap;justify-content:center;margin-top:25px;">
                    <button class="celebrate-btn btn-primary" onclick="window.kiddoCelebration.handleAction('replay')" style="font-size:16px;padding:12px 25px;">
                        🔄 再玩一次
                    </button>
                    <button class="celebrate-btn btn-info" onclick="window.kiddoCelebration.handleAction('next')" style="font-size:16px;padding:12px 25px;">
                        ➡️ 下一关
                    </button>
                    <button class="celebrate-btn btn-secondary" onclick="window.kiddoCelebration.handleAction('select')" style="font-size:16px;padding:12px 25px;">
                        📋 选择关卡
                    </button>
                </div>
                
                <div style="margin-top:20px;">
                    <button class="share-btn" onclick="window.kiddoCelebration.showShareModal()">
                        📱 分享成就
                    </button>
                </div>
            </div>
        `;

        document.body.appendChild(modal);
        
        // 显示动画
        setTimeout(() => modal.classList.add('active'), 50);
        
        // 开始庆祝效果
        this.createConfetti();
        this.createStarRain();
        this.playSound('win');
        
        // 播放星星音效
        setTimeout(() => {
            for (let i = 0; i < stars; i++) {
                setTimeout(() => this.playSound('star'), i * 300);
            }
        }, 500);

        // 保存回调
        this.callbacks = callbacks;
    }

    handleAction(action) {
        this.closeWinModal();
        if (this.callbacks && this.callbacks[action]) {
            this.callbacks[action]();
        }
    }

    closeWinModal() {
        const modal = document.getElementById('kiddo-win-modal');
        if (modal) {
            modal.classList.remove('active');
            setTimeout(() => modal.remove(), 500);
        }
    }

    // 分享模态框
    showShareModal() {
        const shareText = `🎮 我在KiddoCode完成了一个关卡，获得了好多星星！快来一起玩吧！`;
        
        const modal = document.createElement('div');
        modal.id = 'share-modal';
        modal.style.cssText = `
            position:fixed;top:0;left:0;width:100%;height:100%;
            background:rgba(0,0,0,0.7);display:flex;justify-content:center;
            align-items:center;z-index:20000;
        `;
        
        modal.innerHTML = `
            <div style="
                background:white;padding:30px;border-radius:20px;text-align:center;
                max-width:400px;
            ">
                <h3 style="color:#2d3436;margin-bottom:20px;">📱 分享成就</h3>
                <p style="color:#636e72;margin-bottom:20px;">${shareText}</p>
                
                <div style="display:flex;gap:15px;flex-wrap:wrap;justify-content:center;margin-bottom:20px;">
                    <button onclick="window.kiddoCelebration.copyShareText()" style="
                        background:linear-gradient(135deg,#667eea,#764ba2);color:white;
                        padding:12px 20px;border:none;border-radius:15px;
                        font-weight:bold;cursor:pointer;
                    ">📋 复制文本</button>
                    <button onclick="window.kiddoCelebration.closeShareModal()" style="
                        background:#95a5a6;color:white;padding:12px 20px;
                        border:none;border-radius:15px;font-weight:bold;cursor:pointer;
                    ">❌ 关闭</button>
                </div>
            </div>
        `;
        
        document.body.appendChild(modal);
    }

    copyShareText() {
        const shareText = `🎮 我在KiddoCode完成了一个关卡，获得了好多星星！快来一起玩吧！`;
        if (navigator.clipboard) {
            navigator.clipboard.writeText(shareText).then(() => {
                alert('✅ 复制成功！给家人看看你的成就吧！');
            });
        } else {
            alert('✅ 请手动复制: ' + shareText);
        }
        this.closeShareModal();
    }

    closeShareModal() {
        const modal = document.getElementById('share-modal');
        if (modal) modal.remove();
    }

    // 成就系统
    getAchievementStorage() {
        const saved = localStorage.getItem('kiddocode_achievements');
        if (saved) {
            return JSON.parse(saved);
        }
        return { badges: [], totalStars: 0, levelsCompleted: 0, totalPlays: 0 };
    }

    saveAchievement(data) {
        const achievements = this.getAchievementStorage();
        achievements.totalStars += data.stars || 0;
        achievements.levelsCompleted += data.levelCompleted ? 1 : 0;
        achievements.totalPlays++;
        
        const newBadges = this.checkNewBadges(achievements);
        newBadges.forEach(badge => {
            if (!achievements.badges.includes(badge)) {
                achievements.badges.push(badge);
            }
        });
        
        localStorage.setItem('kiddocode_achievements', JSON.stringify(achievements));
        return { achievements, newBadges };
    }

    checkNewBadges(achievements) {
        const badges = [];
        if (achievements.levelsCompleted >= 1) badges.push('🎮 新手玩家');
        if (achievements.levelsCompleted >= 5) badges.push('🏅 探索者');
        if (achievements.levelsCompleted >= 10) badges.push('🏆 冒险者');
        if (achievements.totalStars >= 10) badges.push('⭐ 星星收集者');
        if (achievements.totalStars >= 30) badges.push('🌟 星星大师');
        if (achievements.totalPlays >= 20) badges.push('💪 勤奋学习者');
        return badges;
    }

    showBadgeNotification(badgeName) {
        const notification = document.createElement('div');
        notification.style.cssText = `
            position:fixed;top:20px;right:20px;background:linear-gradient(135deg,#feca57,#ff9f43);
            color:#2d3436;padding:15px 25px;border-radius:15px;
            box-shadow:0 5px 20px rgba(0,0,0,0.2);z-index:30000;
            animation:badge-earned 0.8s ease-out;
        `;
        notification.innerHTML = `
            <div style="display:flex;align-items:center;gap:10px;">
                <span style="font-size:30px;">${badgeName.split(' ')[0]}</span>
                <div>
                    <div style="font-weight:bold;">获得新徽章！</div>
                    <div style="font-size:0.9rem;">${badgeName}</div>
                </div>
            </div>
        `;
        
        document.body.appendChild(notification);
        setTimeout(() => notification.remove(), 4000);
    }
}

window.kiddoCelebration = new KiddoCelebration();
