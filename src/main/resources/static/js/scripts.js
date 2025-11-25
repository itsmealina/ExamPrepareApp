document.addEventListener("DOMContentLoaded", () => {
    const timerEl = document.querySelector(".timer");
    if (!timerEl) return;

    let time = parseInt(timerEl.textContent.split(":")[1]);
    const interval = setInterval(() => {
        time--;
        timerEl.textContent = `00:${time.toString().padStart(2, '0')}`;
        if (time <= 0) {
            clearInterval(interval);
            document.querySelector("form").submit();
        }
    }, 1000);
});