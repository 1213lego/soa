import 'date-fns';
import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
	Dialog,
	DialogActions,
	DialogContent,
	DialogContentText,
	DialogTitle,
	withStyles,
	Fab,
	Tooltip,
	TextField
} from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';
import DateFnsUtils from '@date-io/date-fns';
import { MuiPickersUtilsProvider, KeyboardDatePicker } from '@material-ui/pickers';
import Firestore from '../../api/FirestoreDb';
const styles = (theme) => ({
	dialogContent: {
		overflowY: 'hidden'
	}
});
class AddEditorialDialog extends Component {
	constructor(props) {
		super(props);
		this.state = {
			editorial: {
				country: '',
				foundationDate: new Date(),
				name: '',
				phone: '',
				nit: ''
			}
		};
		this.handleChange = this.handleChange.bind(this);
		this.handleDateChange = this.handleDateChange.bind(this);
		this.saveEditorial = this.saveEditorial.bind(this);
	}
	handleChange(e) {
		this.setState({
			editorial: {
				...this.state.editorial,
				[e.target.name]: e.target.value
			}
		});
	}
	handleDateChange = (date) => {
		console.log(date);
		this.setState({
			editorial: {
				...this.state.editorial,
				foundationDate: date
			}
		});
	};
	async saveEditorial(e) {
		e.preventDefault();
		try {
			await Firestore.saveEditorial(this.state.editorial);
			this.props.onClose();
		} catch (e) {
			console.log(e);
		}
	}
	render() {
		const { classes, open, onClose } = this.props;
		return (
			<Dialog maxWidth="md" open={open} onClose={onClose}>
				<DialogTitle>Add Editorial</DialogTitle>
				<DialogContent className={classes.dialogContent}>
					<DialogContentText>Please fill out the from below</DialogContentText>
					<form>
						<TextField
							label="Nit"
							name="nit"
							value={this.state.editorial.nit}
							onChange={this.handleChange}
							fullWidth
							autoFocus
							margin="dense"
						/>
						<br />
						<TextField
							label="Name"
							name="name"
							value={this.state.editorial.name}
							onChange={this.handleChange}
							fullWidth
							margin="dense"
						/>
						<br />
						<TextField
							label="Country"
							name="country"
							value={this.state.editorial.country}
							onChange={this.handleChange}
							fullWidth
							margin="dense"
						/>
						<br />
						<TextField
							label="Phone"
							name="phone"
							type="number"
							value={this.state.editorial.phone}
							onChange={this.handleChange}
							fullWidth
							margin="dense"
						/>
						<br />
						<MuiPickersUtilsProvider utils={DateFnsUtils}>
							<KeyboardDatePicker
								margin="dense"
								id="date-picker-dialog"
								label="Date picker dialog"
								format="MM/dd/yyyy"
								value={this.state.editorial.foundationDate}
								onChange={this.handleDateChange}
								KeyboardButtonProps={{
									'aria-label': 'change date'
								}}
							/>
						</MuiPickersUtilsProvider>
					</form>
				</DialogContent>
				<DialogActions>
					<Tooltip title="Add" aria-label="add">
						<Fab color="primary" onClick={this.saveEditorial}>
							<SaveIcon />
						</Fab>
					</Tooltip>
				</DialogActions>
			</Dialog>
		);
	}
}
AddEditorialDialog.prototypes = {
	classes: PropTypes.object.isRequired
};
export default withStyles(styles)(AddEditorialDialog);
