
// // // // // // // // console.log(number1 + number2);

// // // // // // // function addNumbers() {

// // // // // // //     let number1 = 10;
// // // // // // //     let number2 = 20;
// // // // // // //     console.log(number1 + number2)
// // // // // // // }
// // // // // // // {
// // // // // // //     var number1 = 10;
// // // // // // //     let number2 = 20;
// // // // // // //     const number3 = 30;   
// // // // // // // }

// // // // // // // console.log(number1);
// // // // // // // // console.log(number2); 
// // // // // // // console.log(number3); // This will throw an error because number3 is not defined outside the block

// // // // // // // // addNumbers();

// // // // // // var number1 = 10;
// // // // // // console.log(typeof number1); // "number"
// // // // // // var number2 = "20";
// // // // // // console.log(typeof number2); // "string"
// // // // // // var number3 = true;
// // // // // // console.log(typeof number3); // "boolean"
// // // // // // var number4 = null;
// // // // // // console.log(typeof number4); // "object" (this is a known JavaScript quirk)
// // // // // // var number5;
// // // // // // console.log(typeof number5); // "undefined"
// // // // // // var number6 = Symbol("unique");
// // // // // // console.log(typeof number6); // "symbol"
// // // // // // var number7 = BigInt(12345678901234567890n);
// // // // // // console.log(typeof number7); // "bigint"
// // // // // // console.log("Hello, World!"); // This will print "Hello, World!" to the console
// // // // // // console.log("This is a string"); // This will print "This is a string" to the console
// // // // // // console.log(42); // This will print the number 42 to the console
// // // // // // console.log(true); // This will print true to the console
// // // // // // console.log(null); // This will print null to the console
// // // // // // console.log(undefined); // This will print undefined to the console
// // // // // // console.log(Symbol("description")); // This will print a symbol with the description "description"
// // // // // // // console.log(BigInt(12345678901234567890n)); // This will print a BigInt value

// // // // // let firstname = "Mahek";
// // // // // let lastname = "Morzaria";


// // // // // console.log(firstname + " " + lastname); // Concatenation using the + operator
// // // // // console.log(`${firstname} ${lastname}`); // Template literals for string interpolation
// // // // // console.log(firstname.concat(" ", lastname)); // Using the concat() method

// // // // // console.log(firstname.length); // Length of the string
// // // // // console.log(firstname.toUpperCase()); // Convert to uppercase
// // // // // console.log(firstname.toLowerCase()); // Convert to lowercase
// // // // // console.log(firstname.indexOf("a")); // Index of the first occurrence of 'a'
// // // // // console.log(firstname.lastIndexOf("a")); // Index of the last occurrence of 'a'
// // // // // console.log(firstname.includes("Ma")); // Check if the string includes 'Ma'
// // // // // console.log(firstname.startsWith("Ma")); // Check if the string starts with 'Ma'
// // // // // console.log(firstname.endsWith("ek")); // Check if the string ends with 'ek'
// // // // // console.log(firstname.slice(1, 4)); // Extract a substring from index 1 to 4
// // // // // console.log(firstname.replace("Ma", "Me")); // Replace 'Ma' with 'Me'
// // // // // console.log(firstname.split("e")); // Split the string by 'e'
// // // // // console.log(firstname.trim()); // Remove whitespace from both ends
// // // // // console.log(firstname.charAt(2)); // Get the character at index 2   
// // // // // console.log(firstname.charCodeAt(2)); // Get the Unicode of the character at index 2
// // // // // console.log(firstname.match(/a/g)); // Match all occurrences of 'a' 
// // // // // console.log(firstname.search("a")); // Search for 'a' and return its index
// // // // // console.log(firstname.repeat(3)); // Repeat the string 3 times
// // // // // console.log(firstname.localeCompare("Mahek")); // Compare two strings
// // // // // console.log(firstname.split("")); // Split the string into an array of characters
// // // // // console.log(firstname.toString()); // Convert to string (redundant here, but useful for other types)


// // // // const title = "Programming in C";
// // // // const author = "balaguru samy";
// // // // const year = 2025;

// // // // const result = `Title: ${title} is written by ${author} in the year ${year}.`;
// // // // console.log(result);

// // // // players = ["Rohit", "Virat", "Hardik", "Jasprit"];
// // // // console.log(players.join(", ")); // Join array elements into a string
// // // // console.log(players.length); // Get the number of elements in the array
// // // // console.log(players[0]); // Access the first element
// // // // console.log(players[players.length - 1]); // Access the last element
// // // // console.log(players.indexOf("Virat")); // Find the index of "Virat"
// // // // console.log(players.includes("Hardik")); // Check if "Hardik" is in the array
// // // // console.log(players.slice(0, 3)); // Get a sub-array from index 1

// // // // coaches = ["Ravi", "Suresh", "Ajay"];
// // // // console.log(coaches.concat(players)); // Concatenate two arrays
// // // // console.log(players.reverse()); // Reverse the array
// // // // console.log(players.sort()); // Sort the array alphabetically
// // // // console.log(players.splice(1, 2)); // Remove 2 elements starting from index 1
// // // // console.log(players); // Display the modified array after splice
// // // // console.log(players.push("Rohit")); // Add "Rohit" to the end of the array
// // // // console.log(players.pop()); // Remove the last element and return it
// // // // console.log(players.shift()); // Remove the first element and return it
// // // // console.log(players.unshift("Rohit")); // Add "Rohit" to the beginning  
// // // // console.log(players); // Display the modified array after unshift
// // // // console.log(players.fill("Rohit", 1, 3)); // Fill elements from index 1 to 3 with "Rohit"
// // // // console.log(players); // Display the modified array after fill  
// // // // console.log(players.every(player => player.length > 3)); // Check if all players have names longer than 3 characters
// // // // console.log(players.some(player => player.startsWith("R"))); // Check if any player starts with "R"
// // // // console.log(players.filter(player => player.includes("a"))); // Filter players whose names contain "a"
// // // // console.log(players.map(player => player.toUpperCase())); // Convert all player names to uppercase
// // // // console.log(players.reduce((acc, player) => acc + player.length, 0)); // Calculate the total length of all player names
// // // // console.log(players.find(player => player.startsWith("H"))); // Find the first player whose name starts with "H"
// // // // console.log(players.findIndex(player => player.startsWith("H"))); // Find the index of the first player whose name starts with "H"
// // // // console.log(players.toString()); // Convert the array to a string



// // // //create an array of values of products, calculatesum of all products in list after applying a tax using function without loop.



// // // // function calculateTotalWithTax(products, taxRate) {
// // // //     let total = 0;
// // // //     for (let i = 0; i < products.length; i++) {
// // // //         total += products[i];
// // // //     }
// // // //     return total + (total * taxRate);
// // // // }
// // // // const products = [100, 200, 300, 400];
// // // // const taxRate = 0.18;
// // // // const totalWithTax = calculateTotalWithTax(products, taxRate);
// // // // console.log(`Total with tax: ${totalWithTax}`);

// // // // Filter even and odd numbers, and find numbers divisible by 3 but not by 2
// // // // function EvenOdd(nums) {
// // // //   let evens = [];
// // // //   let odds = [];
// // // //   let div = [];

// // // //   for (let num of nums) {
// // // //     if (num % 2 === 0) {
// // // //       evens.push(num);
// // // //     } else {
// // // //       odds.push(num);
// // // //       if (num % 3 === 0) {
// // // //         div.push(num);
// // // //       }
// // // //     }
// // // //   }

// // // //   console.log("Even:", evens);
// // // //   console.log("Odd:", odds);
// // // //   console.log("Divisible by 3 but not 2:", div);
// // // // }


// // // // const nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15];
// // // // EvenOdd(nums);




// // // // // Find duplicates in an array

// // // // function findDuplicates(arr) {
// // // //   let duplicates = [];

// // // //   for (let i = 0; i < arr.length; i++) {
// // // //     if (arr.indexOf(arr[i]) !== i && !duplicates.includes(arr[i])) {
// // // //       duplicates.push(arr[i]);
// // // //     }
// // // //   }

// // // //   return duplicates;
// // // // }


// // // // const arr = [1, 2, 2, 3, 4, 3, 5, 1];
// // // // const result1 = findDuplicates(arr);
// // // // console.log("Duplicates:", result1);


// // // // Remove whitespaces from a string

// // // function removeWhitespaces(str) {
// // //   return str.replace(/\s+/g, '');
// // // }
// // // const input = "a b c d";
// // // const output = removeWhitespaces(input);
// // // console.log("Output:", output); 


// // // // Check if two strings are rotations of each other
// // // function areRotations(str1, str2) {
// // //   if (str1.length !== str2.length) return false;
// // //   return (str1 + str1).includes(str2);
// // // }


// // // const str1 = "abcd";
// // // const str2 = "cdab";
// // // const str3 = "cab";
// // // const result2= areRotations(str1, str2);
// // // const result3 = areRotations(str1, str3);
// // // console.log("Are rotations?", result2); 
// // // console.log("Are rotations?", result3); 


// // let user = {
// //     name: "Mahek",  
// //     age: 20,
// //     email: "mahek@gmail.com",
// //     blogs: ["javascript", "python", "java"],

// //     login: function() {
// //         console.log("User logged in");
// //         console.log(this.name + " is logged in");

        
// //     },

// //     logout:() => {
// //             console.log("User logged out");
// //             console.log(user.name + " is logged out");      
// //         }
// // }

// // console.log(user.name); // Accessing property using dot notation
// // console.log(user["age"]); // Accessing property using bracket notation
// // console.log(typeof(user));
// // console.log(user.blogs[0]); // Accessing an element in the array within the object
// // console.log(user.blogs.length); // Getting the length of the array within the object
// // user.name = "Mahek Morzaria"; // Modifying a property
// // console.log(user.name); // Displaying the modified property
// // console.log(user.blogs.push("C++")); // Adding a new blog to the array
// // console.log(user.blogs); // Displaying the updated array of blogs

// // const key = "key";

// // console.log(user.key); // This will return undefined because 'key' is not a property of the user object
// // console.log(user[key]); // This will also return undefined because 'key' is not a property



// // user.login(); // Calling the login method of the user object
// // user.logout(); // Calling the logout method of the user object
// // console.log(user); // Displaying the entire user object


// // //creating a blog object with methods to publish and unpublish the blog and create a array of blogs and print them on console using a user defined method.

// // function Blog(title, content, author) {
// //   this.title = title;
// //   this.content = content;
// //   this.author = author;
// //   this.published = false;
// //   this.publish = function() {
// //     this.published = true;
// //     console.log(`Blog "${this.title}" published.`);
// //   };
// //   this.unpublish = function() {
// //     this.published = false;
// //     console.log(`Blog "${this.title}" unpublished.`);
// //   };
// // }

// // const blogs = [
// //   new Blog("ncjwkenf", "nkjsndce", "Mahek"),
// //   new Blog("scdne", "mnds cjs f", "Harshad"),
// //   new Blog("njkesdcnj", "R,mdn csdkmnj", "Nikul"),
// //   new Blog("njsndc", "njsndc", "Harshil"),
// //   new Blog("njsndc", "njsndc", "Dharmi")
// // ];

// // function printBlogs(blogArray) {
// //   blogArray.forEach(blog => {
// //     console.log(`Title: ${blog.title}\nContent: ${blog.content}\nAuthor: ${blog.author}\nPublished: ${blog.published}\n`);
// //   });
// // }

// // printBlogs(blogs);
// // blogs[0].publish();
// // blogs[1].publish();
// // printBlogs(blogs);




// // const blog1 = {
// //   title: "ncjwkenf",
// //   content: "nkjsndce",
// //   author: "Mahek",
// //   published: false,
// //   publish: function() {
// //     this.published = true;
// //     console.log(`Blog "${this.title}" published.`);
// //   },
// //   unpublish: function() {
// //     this.published = false;
// //     console.log(`Blog "${this.title}" unpublished.`);
// //   }
// // };

// // const blog2 = {
// //   title: "scdne",
// //   content: "mnds cjs f",
// //   author: "Harshad",
// //   published: false,
// //   publish: function() {
// //     this.published = true;
// //     console.log(`Blog "${this.title}" published.`);
// //   },
// //   unpublish: function() {
// //     this.published = false;
// //     console.log(`Blog "${this.title}" unpublished.`);
// //   }
// // };

// // const blog3 = {
// //   title: "njkesdcnj",
// //   content: "R,mdn csdkmnj",
// //   author: "Nikul",
// //   published: false,
// //   publish: function() {
// //     this.published = true;
// //     console.log(`Blog "${this.title}" published.`);
// //   },
// //   unpublish: function() {
// //     this.published = false;
// //     console.log(`Blog "${this.title}" unpublished.`);
// //   }
// // };

// // const blog4 = {
// //   title: "njsndc",
// //   content: "njsndc",
// //   author: "Harshil",
// //   published: false,
// //   publish: function() {
// //     this.published = true;
// //     console.log(`Blog "${this.title}" published.`);
// //   },
// //   unpublish: function() {
// //     this.published = false;
// //     console.log(`Blog "${this.title}" unpublished.`);
// //   }
// // };

// // const blog5 = {
// //   title: "njsndc",
// //   content: "njsndc",
// //   author: "Dharmi",
// //   published: false,
// //   publish: function() {
// //     this.published = true;
// //     console.log(`Blog "${this.title}" published.`);
// //   },
// //   unpublish: function() {
// //     this.published = false;
// //     console.log(`Blog "${this.title}" unpublished.`);
// //   }
// // };

// // const blogsArr = [blog1, blog2, blog3, blog4, blog5];

// // function printBlogs(blogArray) {
// //   blogArray.forEach(blog => {
// //     console.log(`Title: ${blog.title}\nContent: ${blog.content}\nAuthor: ${blog.author}\nPublished: ${blog.published}\n`);
// //   });
// // }

// // printBlogs(blogsArr);
// // blogsArr[0].publish();
// // blogsArr[1].publish();
// // printBlogs(blogsArr);


// class User {
//   constructor(username) {
//     this.username = username;
//     this.loggedIn = false;
//     this.blogs = [];
//   }

//   login() {
//     this.loggedIn = true;
//     console.log(`${this.username} logged in.`);
//   }

//   logout = () => {
//     this.loggedIn = false;
//     console.log(`${this.username} logged out.`);
//   };

//   createBlog(title, content, author) {
//     const blog = {
//       title,
//       content,
//       author,
//       published: true
//     };
//     this.blogs.push(blog);
//     console.log(`Blog titled "${title}" created and published.`);
//   }

//   unpublishBlog(title) {
//     const blog = this.blogs.find(b => b.title === title);
//     if (blog) {
//       blog.published = false;
//       console.log(`Blog titled "${title}" is now unpublished.`);
//     } else {
//       console.log(`Blog titled "${title}" not found.`);
//     }
//   }

//   listBlogs() {
//     this.blogs.forEach(blog => {
//       console.log(`Title: ${blog.title}, Published: ${blog.published}`);
//     });
//   }

//   searchBlogs(keyword) {
//     const results = this.blogs.filter(blog =>
//       blog.title.includes(keyword) || blog.content.includes(keyword)
//     );
//     console.log(`Blogs containing "${keyword}":`);
//     results.forEach(blog => {
//       console.log(`- ${blog.title}`);
//     });
//   }

//   countPublishedBlogs() {
//     const count = this.blogs.filter(blog => blog.published).length;
//     console.log(`Number of published blogs: ${count}`);
//     return count;
//   }
// }


// const user = new User("Mahek Morzaria");
// user.login();
// user.createBlog("My First Blog");
// user.createBlog("Second Blog");
// user.listBlogs();
// user.unpublishBlog("Second Blog");
// user.listBlogs();
// user.countPublishedBlogs();
// user.logout();


// let players = ["Virat", "Rohit", "Sachin", "Rahul"];

// const playerTable = document.querySelector('.table tbody');
// let count = 1;
// // Example of getElementById usage:
// const tableElement = document.getElementById('playerTable');
// players.forEach(player => {
//     playerTable.innerHTML += `<tr><td>${count++}</td><td>${player}</td></tr>`;
// });


// const allP = document.querySelectorAll('p');
// allP.forEach(para => {
//     const text = para.textContent.trim().toLowerCase();
//     if (text.includes('error')) 
//         para.classList.add('error');
//     if (text.includes('success'))
//         para.classList.add('success');
// });


const displayBtn = document.querySelector(".btnDisplay");
const nameTxt = document.querySelector(".nameTxt");
const displayDiv = document.querySelector(".nameDiv");

displayBtn.addEventListener("click", () => {
  let name = nameTxt.value;
  displayDiv.innerText = name;
});



const inputTxt = document.querySelector('.inputTxt');
const outputTxt = document.querySelector('.outputTxt');

inputTxt.addEventListener('input', () => {
  outputTxt.value = inputTxt.value;
});




