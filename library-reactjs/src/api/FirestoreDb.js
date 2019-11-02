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
		return this._db.collection("editorials")
			.doc(editorial.nit)
			.set(editorial);
	}
	getEditorials(){
		return this._db.collection("editorials")
			.get()
	}
	get db() {
		return this._db;
	}
}
export default new Firestore();
