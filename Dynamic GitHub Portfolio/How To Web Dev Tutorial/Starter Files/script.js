let menu = document.querySelector('#menu');
let links = document.querySelector('.links');

menu.onclick = () => {
  menu.classList.toggle('bx-x');
  links.classList.toggle('active');
};
