import React, { Component } from 'react'

class AddEditorial extends Component {
    constructor(props){
        super(props);
        this.state = {
        }
        this.saveEditorial = this.saveEditorial.bind(this)
    }
    saveEditorial(){

    }
    render() {
        return (
            <div>
                <h2> Add Editorial </h2>
            </div>
        )
    }
}
export default AddEditorial;
