const validGuesses = [5, 12, 27, 29, 30, 31, 35, 37, 90, 100];
const maxAttempts = 5;
let attemptsLeft = maxAttempts;


function shuffleArray(arr) {
  for (let i = arr.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [arr[i], arr[j]] = [arr[j], arr[i]];
  }
  return arr;
}

const shuffledGuesses = shuffleArray([...validGuesses]); 
const randomNumber = shuffledGuesses[Math.floor(Math.random() * shuffledGuesses.length)];

document.getElementById("targetNumber").textContent = randomNumber;
const message = document.getElementById("message");
const remaining = document.getElementById("remaining");
const buttonContainer = document.getElementById("buttonContainer");

remaining.textContent = `Attempts Left: ${attemptsLeft}`;

shuffledGuesses.forEach(number => {
  const btn = document.createElement("button");
  btn.textContent = number;
  btn.className = "number-button";
  btn.setAttribute("data-number", number);
  btn.onclick = () => checkGuess(parseInt(number), btn);
  buttonContainer.appendChild(btn);
});

function checkGuess(guess, btn) {
  btn.style.color = "white"; 

  if (guess === randomNumber) {
    message.textContent = "Correct.";
    btn.classList.add("correct");
    disableAllButtons();
  } else {
    attemptsLeft--;
    btn.classList.add("wrong");
    btn.disabled = true;

    if (attemptsLeft === 0) {
      message.textContent = `Game Over! The correct number was ${randomNumber}.`;
      revealCorrectButton();
      disableAllButtons();
    } else {
      message.textContent = "Wrong button. Try again.";
    }

    remaining.textContent = `Attempts Left: ${attemptsLeft}`;
  }
}

function disableAllButtons() {
  const buttons = document.querySelectorAll(".number-button");
  buttons.forEach(btn => btn.disabled = true);
}

function revealCorrectButton() {
  const buttons = document.querySelectorAll(".number-button");
  buttons.forEach(btn => {
    if (parseInt(btn.getAttribute("data-number")) === randomNumber) {
      btn.style.color = "white";
      btn.classList.add("correct");
    }
  });
}
