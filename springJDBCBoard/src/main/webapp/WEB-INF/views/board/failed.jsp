<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 실패</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">

<style>
/* 🌌 움직이는 배경 + 어두운 공포 느낌 */
body {
    margin: 0;
    min-height: 100vh;
    overflow: hidden;
    background: linear-gradient(270deg, #0d0d0d, #1a1a1a, #111111, #000000);
    background-size: 800% 800%;
    animation: bgGradient 20s ease infinite;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #fff;
    font-family: 'Noto Sans KR', sans-serif;
}

@keyframes bgGradient {
    0% { background-position: 0% 50%; }
    50% { background-position: 100% 50%; }
    100% { background-position: 0% 50%; }
}

/* ✨ 파티클 + 번개용 캔버스 */
#particle, #lightning {
    position: fixed;
    inset: 0;
    z-index: 1;
}

/* 💀 카드 */
.card-box {
    position: relative;
    z-index: 2;
    background: rgba(20,20,20,0.85);
    backdrop-filter: blur(12px);
    border-radius: 20px;
    padding: 45px;
    text-align: center;
    box-shadow: 0 25px 60px rgba(0,0,0,.55);
    animation: fadeUp .8s ease;
}

@keyframes fadeUp {
    from { opacity: 0; transform: translateY(40px); }
    to { opacity: 1; transform: translateY(0); }
}

.icon-fail {
    font-size: 4rem;
    color: #ff4c4c;
    text-shadow: 0 0 15px rgba(255,76,76,.7);
}

/* 🔘 버튼 */
.btn-main {
    background: linear-gradient(135deg, #ff0000, #550000);
    border: none;
    color: #fff;
    font-weight: 700;
    padding: 12px 36px;
    border-radius: 30px;
    transition: .3s;
}

.btn-main:hover {
    transform: translateY(-2px);
    box-shadow: 0 12px 30px rgba(255,0,0,.6);
}
</style>
</head>

<body>

<!-- ✨ 파티클 -->
<canvas id="particle"></canvas>
<!-- ⚡ 번개 -->
<canvas id="lightning"></canvas>

<!-- ❌ 실패 카드 -->
<div class="card-box">
    <div class="icon-fail mb-3">
        <i class="bi bi-x-circle-fill"></i>
    </div>

    <h2 class="fw-bold mb-3">게시글 등록 실패</h2>
    <p class="mb-4">
        요청을 처리하는 중 문제가 발생했습니다.<br>
        다시 시도해주세요.
    </p>

    <div class="d-flex justify-content-center gap-3">
        <a href="/board/write" class="btn btn-main">다시 작성</a>
        <a href="/board/list" class="btn btn-main">목록</a>
    </div>
</div>

<!-- ✨ 파티클 스크립트 -->
<script>
const canvas = document.getElementById("particle");
const ctx = canvas.getContext("2d");

const lightningCanvas = document.getElementById("lightning");
const lctx = lightningCanvas.getContext("2d");

function resize() {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    lightningCanvas.width = window.innerWidth;
    lightningCanvas.height = window.innerHeight;
}
resize();
window.addEventListener("resize", resize);

// 🌟 파티클
const particles = [];
const COUNT = 80;
class Dot {
    constructor() { this.reset(); }
    reset() {
        this.x = Math.random() * canvas.width;
        this.y = Math.random() * canvas.height;
        this.r = Math.random() * 2 + 0.5;
        this.v = Math.random() * 0.4 + 0.2;
    }
    update() {
        this.y -= this.v;
        if(this.y < 0) this.reset();
    }
    draw() {
        ctx.beginPath();
        ctx.arc(this.x,this.y,this.r,0,Math.PI*2);
        ctx.fillStyle = "rgba(255,255,255,0.7)";
        ctx.fill();
    }
}
for(let i=0;i<COUNT;i++) particles.push(new Dot());

// ⚡ 번개
let flashTime = 0;
function lightning() {
    if(Math.random() < 0.02 && flashTime <= 0) flashTime = 5;
    if(flashTime > 0) {
        lctx.fillStyle = "rgba(255,255,255," + (flashTime/5) + ")";
        lctx.fillRect(0,0,lightningCanvas.width, lightningCanvas.height);
        flashTime--;
    } else {
        lctx.clearRect(0,0,lightningCanvas.width, lightningCanvas.height);
    }
}

function animate() {
    ctx.clearRect(0,0,canvas.width, canvas.height);
    particles.forEach(p => { p.update(); p.draw(); });
    lightning();
    requestAnimationFrame(animate);
}
animate();
</script>

</body>
</html>