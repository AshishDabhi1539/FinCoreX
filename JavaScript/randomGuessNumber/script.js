const validGuesses = [5, 12, 27, 29, 30, 31, 35, 37, 90, 100];
const maxAttempts = 3;
let attemptsLeft = maxAttempts;
const randomNumber = validGuesses[Math.floor(Math.random() * validGuesses.length)];


function checkGuess() {
  const input = document.getElementById("guessInput");
  const guess = parseInt(input.value);
  const message = document.getElementById("message");
  const remaining = document.getElementById("remaining");

  if (!validGuesses.includes(guess)) {
    message.textContent = "Invalid guess.";
    return;
  }

  if (guess === randomNumber) {
  message.textContent = "You guessed right!";
  disableInput();
} else {
  attemptsLeft--;
  if (attemptsLeft === 0) {
    message.textContent = `Wrong! The correct number was ${randomNumber}. Game over.`;
    disableInput();
  } else {
    message.textContent = "Wrong guess. Try again.";
  }
  remaining.textContent = `Remaining chances: ${attemptsLeft}`;
}

  input.value = "";
}

function disableInput() {
  document.getElementById("guessInput").disabled = true;
}

document.getElementById("remaining").textContent = `Remaining chances: ${attemptsLeft}`;