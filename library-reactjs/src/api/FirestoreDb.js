import firebase from 'firebase';
const firebaseConfig = {
	apiKey: 'AIzaSyDY9jIHnvq7hmVIIkBhGKnQo4EZnInyMKI',
	authDomain: 'soa-libreria.firebaseapp.com',
	databaseURL: 'https://soa-libreria.firebaseio.com',
	projectId: 'soa-libreria',
	storageBucket: 'soa-libreria.appspot.com',
	messagingSenderId: '506684659427',
	appId: '1:506684659427:web:806e7029642c61c5e497d6'
};
firebase.initializeApp(firebaseConfig);
class Firestore {
	constructor(){
		this._db = firebase.firestore();
	}
	saveEditorial(editorial){
		return firebase.firestore()
		.collection("editorials")
			.doc(editorial.nit)
			.set(editorial);
	}
	deleteEditorial(editorial){
		return firebase.firestore()
		.collection("editorials")
		.doc(editorial.nit)
		.delete();
	}
	getEditorials(){
		return this._db.collection("editorials")
			.get();
	}
	saveBookFromPublisher(publisher, newBook){
		return this._db.collection("editorials")
			.doc(publisher.nit)
			.collection("books")
			.doc(newBook.ISBN)
			.set(newBook);
	}
	getBooksFromPublisher(publisher) {
		return this._db.collection("editorials")
			.doc(publisher.nit)
			.collection("books")
			.get();
	}
	get db() {
		return this._db;
	}
}
export default new Firestore();
