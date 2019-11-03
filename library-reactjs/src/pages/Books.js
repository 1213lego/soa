import React, { Component } from "react";
import PropTypes from "prop-types";
import MaterialTable from 'material-table';
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
    try {
	  const queryPublisher = await Firestore.getEditorials();
	  let publishers = [];
	  queryPublisher.forEach(function(doc) {
		publishers.push(doc.data());
	  });
	  this.setState({ publishers: [...publishers] });

	  this.state.currentPublisher = publishers[0];

      const queryBooks = await Firestore.getBooksFromPublisher(this.state.currentPublisher);
      let books = [];
      queryBooks.forEach(function(book) {
        books.push(book.data());
      });
      this.setState({ books: [...books] });
    } catch (e) {
      console.log(e);
    }
  }
  async addBook(newBook) {
	try {
		await Firestore.saveBookFromPublisher(this.state.currentPublisher, newBook);
	  } catch (e) {
		console.log(e);
	  }
  }
  render() {
    return (
      <div>
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
						const books = this.state.books;
                        const index = books.indexOf(oldBook);
                        books[index] = newBook;                
                        this.setState({ books }, () => resolve());
						resolve();
				}),
				onRowDelete: oldBook =>
					new Promise((resolve, reject) => {
				})
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
