const userBtn = document.querySelector('.userBtn')
userBtn.addEventListener('click', event => {
  fetchUsers(event)
})
let users
const fetchUsers = async event => {
  try {
    const response = await fetch('https://dummyjson.com/users')
    if (!response.ok) console.log('Error')
    users = await response.json()
    console.log(users.users[0])
    logusers()
  } catch (error) {
    console.log(error)
    
  }
}

function logusers() {
console.log(users)
}