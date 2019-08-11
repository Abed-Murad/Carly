var config = {
  apiKey: "AIzaSyDLSWnSDSiRVjP6FegLCY0FOkIhG1Q7SYY",
    authDomain: "carly-e9f27.firebaseapp.com",
    databaseURL: "https://carly-e9f27.firebaseio.com",
    projectId: "carly-e9f27",
    storageBucket: "carly-e9f27.appspot.com",
    messagingSenderId: "599742354231",
    appId: "1:599742354231:web:57d3a922c3c995c1"

};
firebase.initializeApp(config);
const firestore = firebase.firestore();
firestore.settings({
    timestampsInSnapshots: true
});

const colRef = firestore.collection("cities");

const urlField = document.getElementById("urlField");
const imageUrlField = document.getElementById("imageUrlField");
const titleField = document.getElementById("titleField");
const bodyField = document.getElementById("bodyField");
const isVideoCheckBox = document.getElementById("isVideoCheckBox");
const saveButton = document.getElementById("saveButton");

saveButton.addEventListener("click", function () {

    console.log("clicked");


    let id = urlField.value;
    let name = imageUrlField.value;
    let imgUrl = titleField.value;

    colRef.add({
        id: id,
        name: name,
        imgUrl: imgUrl,

    }).then(function () {
        urlField.value = "";
        imageUrlField.value = "";
        titleField.value = "";
        console.log("status Saved!");
    }).catch(function (error) {
        console.log("Got an error: " + error);
    })
});