"use strict";
const messageSpan = document.getElementById("messageSpan");
console.log("index-js kara");
// Ẩn mess sau 4s
function disMessage() {
  messageSpan.classList.add("hide");
}
setTimeout(disMessage, 4000);

//chọn danh mục tìm kiếm
const search1 = document.getElementById("search1");
const search2 = document.getElementById("search2");
const search3 = document.getElementById("search3");
const whereSearch = document.getElementById("whereSearch");

search1.addEventListener("click", function () {
  search1.classList.remove("btn-warning");
  search1.classList.add("btn-light");
  whereSearch.value = "1";
  console.log(whereSearch.value);
  search2.classList.add("btn-warning");
  search2.classList.remove("btn-light");
  search3.classList.add("btn-warning");
  search3.classList.remove("btn-light");
  console.log("search 1");
});
search2.addEventListener("click", function () {
  search2.classList.remove("btn-warning");
  search2.classList.add("btn-light");
  whereSearch.value = "2";
  console.log(whereSearch.value);
  search1.classList.add("btn-warning");
  search1.classList.remove("btn-light");
  search3.classList.add("btn-warning");
  search3.classList.remove("btn-light");
  console.log("search 2");
});
search3.addEventListener("click", function () {
  search3.classList.remove("btn-warning");
  search3.classList.add("btn-light");
  whereSearch.value = "3";
  console.log(whereSearch.value);
  search2.classList.add("btn-warning");
  search2.classList.remove("btn-light");
  search1.classList.add("btn-warning");
  search1.classList.remove("btn-light");
  console.log("search 3");
});
