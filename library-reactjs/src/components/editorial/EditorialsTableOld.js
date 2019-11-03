import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Table, TableBody, TableCell, TableHead, TableRow, withStyles, Typography, Paper } from '@material-ui/core';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import Firestore from '../../api/FirestoreDb';
const styles = (theme) => ({
	a: {
		display: 'flex',
		justifyContent: 'center'
	},
	root: {
		width: '100%',
		overflowX: 'auto'
	},
	table: {
		minWidth: 650
	}
});

class EditorialList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			editorials: [],
		};
		this.handleClick = this.handleClick.bind(this);
	}
	async componentDidMount() {
		try {
			const querySnapshot = await Firestore.getEditorials();
			let editorials = querySnapshot.docs.map((doc) => {
				const data = doc.data();
				data.foundationDate = data.foundationDate.toDate();
				return data;
			});
			this.setState({ editorials: [ ...editorials ] });
		} catch (e) {
			console.log(e);
		}
	}
	render() {
		const { classes } = this.props;
		return (
			<div>
				<Typography variant="h4" className={classes.a}>
					Publishers details
				</Typography>
				<Paper className={classes.root}>
					<Table aria-label="simple table" className={classes.table}>
						<TableHead>
							<TableRow>
								<TableCell>Nit</TableCell>
								<TableCell align="right">Name</TableCell>
								<TableCell align="right">Country</TableCell>
								<TableCell align="right">Phone</TableCell>
								<TableCell align="right">Foundation date</TableCell>
								<TableCell align="right">Actions</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{this.state.editorials.map((editorial) => (
								<TableRow key={editorial.nit}>
									<TableCell component="th" scope="row">
										{editorial.nit}
									</TableCell>
									<TableCell align="right">{editorial.name}</TableCell>
									<TableCell align="right">{editorial.country}</TableCell>
									<TableCell align="right">{editorial.phone}</TableCell>
									<TableCell align="right">{editorial.foundationDate.toDateString()}</TableCell>
									<TableCell align="right">
										<EditIcon /> <DeleteIcon />
									</TableCell>
								</TableRow>
							))}
						</TableBody>
					</Table>
				</Paper>
			</div>
		);
	}
}
EditorialList.prototypes = {
	classes: PropTypes.object.isRequired
};
export default withStyles(styles)(EditorialList);
