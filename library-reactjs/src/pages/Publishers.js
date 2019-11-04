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
	{ title: 'Nit', field: 'nit', editable: 'onAdd'},
	{ title: 'Name', field: 'name' },
	{ title: 'Country', field: 'country' },
	{ title: 'Phone', field: 'phone', type: 'numeric' },
	{ title: 'Foundation date', field: 'foundationDate', type: 'date' }
];
class EditorialList extends Component {
	constructor(props) {
		super(props);
		this.state = {
			editorials: [],
			open: false
		};
		this.handleClick = this.handleClick.bind(this);
		this.onDataChange = this.onDataChange.bind(this);
		this.algo();
	}
	onDataChange(querySnapshot) {
		let editorials = querySnapshot.docs.map((doc) => {
			const data = doc.data();
			data.foundationDate = data.foundationDate.toDate();
			return data;
		});
		this.setState({ editorials: [ ...editorials ] });
	}
	async algo() {
		const querySnapshot = await Firestore.db.collection('editorials').get();
		querySnapshot.forEach(async (doc) => {
			const queryResult = await doc.ref.collection('books').where('name', '==', '147').get();
			queryResult.forEach((d) => {
				console.log(d.data());
			});
		});
	}
	async componentDidMount() {
		try {
			const querySnapshot = await Firestore.getEditorials();
			this.onDataChange(querySnapshot);
			this.unsubscribeRef = Firestore.db.collection('editorials').onSnapshot(this.onDataChange);
		} catch (e) {
			console.log(e);
		}
	}
	componentWillUnmount() {
		if(this.unsubscribeRef) {
			this.unsubscribeRef();
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
					addRow={Firestore.saveEditorial}
					deleteRow={Firestore.deleteEditorial}
					updateRow={Firestore.saveEditorial}
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
