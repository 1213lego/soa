import React, { Component } from 'react';
import PropTypes from 'prop-types';
import AddIcon from '@material-ui/icons/Add';
import Fab from '@material-ui/core/Fab';
import Tooltip from '@material-ui/core/Tooltip';
import { withStyles } from '@material-ui/core';
import AddEditorialDialog from '../components/editorial/AddEditorialDialog';
import Firestore from '../api/FirestoreDb';
import CustomMaterialTable from '../components/utils/CustomMaterialTable';
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
	},
	absolute: {
		position: 'fixed',
		bottom: theme.spacing(2),
		right: theme.spacing(2)
	}
});
const columns = [
	{ title: 'Nit', field: 'nit' },
	{ title: 'Name', field: 'name' },
	{ title: 'Country', field: 'country' },
	{ title: 'Phone', field: 'phone' },
	{ title: 'Foundation date', field: 'foundationDate' }
];
class EditorialList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			editorials: [],
			open: false
		};
		this.handleClick = this.handleClick.bind(this);
	}
	async componentDidMount() {
		try {
			const querySnapshot = await Firestore.getEditorials();
			let editorials = querySnapshot.docs.map((doc) => {
				const data = doc.data();
				data.foundationDate = data.foundationDate.toDate().toDateString();
				return data;
			});
			this.setState({ editorials: [ ...editorials ] });
		} catch (e) {
			console.log(e);
		}
	}
	handleClick() {
		this.setState({
			open: !this.state.open
		});
	}
	render() {
		const { classes } = this.props;
		return (
			<div>
				<CustomMaterialTable 
				columns={columns} 
				data={this.state.editorials} 
				title="Publishers detatils" 

				/>
				<AddEditorialDialog open={this.state.open} onClose={this.handleClick} />
				<Tooltip title="Add" aria-label="add">
					<Fab color="primary" className={classes.absolute} onClick={this.handleClick}>
						<AddIcon />
					</Fab>
				</Tooltip>
			</div>
		);
	}
}
EditorialList.prototypes = {
	classes: PropTypes.object.isRequired
};
export default withStyles(styles)(EditorialList);
