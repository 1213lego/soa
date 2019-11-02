import React, { Component } from 'react';

class AddEditorial extends Component {
	constructor(props) {
		super(props);
		this.state = {
			country: '',
			foundationDate: '',
			name: '',
			phone: ''
		};
		this.saveEditorial = this.saveEditorial.bind(this);
		this.onChange = this.onChange.bind(this);
	}
	saveEditorial(event) {
        event.preventDefault();
    }
	onChange(event) {
		this.setState({ [event.target.name]: event.target.value });
	}
	render() {
		return (
			<div>
				<h2> Add Editorial </h2>
			</div>
		);
	}
}
export default AddEditorial;
