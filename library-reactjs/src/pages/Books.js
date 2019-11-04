import React, { Component } from "react";
import PropTypes from "prop-types";
import MaterialTable from 'material-table';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import Firestore from "../api/FirestoreDb";
	
class Books extends Component {
  constructor(props) {
    super(props);
    this.state = {
	  publishers: [],
	  books: [],
	  currentPublisher: {},
	  columns: [
		{ title: 'ISBN', field: 'ISBN', sorting: false, editable: 'onAdd' },
		{ title: 'Name', field: 'name',  },
		{ title: 'Author', field: 'author' },
		{ title: 'Pages', field: 'pages', type: 'numeric', sorting: false },
	  ]
    };
  }
  async componentDidMount() {
	this.getPublishers();
  }
  async getPublishers() {
	  try {
		const queryPublisher = await Firestore.getEditorials();
		let publishers = [];
		queryPublisher.forEach(function(doc) {
		  publishers.push(doc.data());
		});
		this.setState({ publishers: [...publishers] });
	  } catch (e) {
		console.log(e);
	}
  }
  async getBooks(publisher) {
	if(this.unsubscribeRef) {
		this.unsubscribeRef();
	}

	this.unsubscribeRef = Firestore.db
	.collection('editorials')
	.doc(publisher.nit)
	.collection('books')
	.onSnapshot(querySnapshot => {
		let books = querySnapshot.docs.map((doc) => doc.data());
		this.setState({ books: [...books] });
	})
  }

  componentWillUnmount() {
	if(this.unsubscribeRef) {
		this.unsubscribeRef();
	}
  }
  
  async addBook(newBook) {
	try {
		if(newBook.pages === "") {
			newBook.pages = 0;
		}
		await Firestore.saveBookFromPublisher(this.state.currentPublisher, newBook);
	  } catch (e) {
		console.log(e);
	  }
  }
  async deleteBook(newBook) {
	try {
		await Firestore.deleteBookFromPublisher(this.state.currentPublisher, newBook);
	  } catch (e) {
		console.log(e);
	  }
  }
  render() {
    return (
      <div>
		<div align="center">
			
		</div>
		<br/>
		<MaterialTable 
			title="Books"	
			columns={this.state.columns}
			data={this.state.books}
			editable={{
				onRowAdd: newBook =>
					new Promise((resolve, reject) => {
						this.addBook(newBook);
						const books = this.state.books;
                        books.push(newBook);
						this.setState({ books }, () => resolve());
				}),
				onRowUpdate: (newBook, oldBook) =>
					new Promise((resolve, reject) => {
						this.addBook(newBook);
						const books = this.state.books;
						books[oldBook.tableData.id] = newBook;
                        this.setState({ books }, () => resolve());
				}),
				onRowDelete: oldBook =>
					new Promise((resolve, reject) => {
						this.deleteBook(oldBook);
						let books = this.state.books;
                        books.splice(oldBook.tableData.id, 1);
						this.setState({ books }, () => resolve());
				})
			}}
		/>
		<br/>
		<Autocomplete
			style={{ width: 300 }}
			options={this.state.publishers}
			getOptionLabel={publisher => publisher.name}
			renderInput={params => (
				<TextField {...params} variant="outlined" label="Publishers" fullWidth />
			)}
			onChange={(event, publisher) => {
				this.setState({ currentPublisher: publisher });
				this.getBooks(publisher);
			}}
		/>
      </div>
    );
  }
}

Books.prototypes = {
  classes: PropTypes.object.isRequired
};
export default Books;
