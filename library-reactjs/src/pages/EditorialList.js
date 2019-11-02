import React, {Component} from 'react';
import PropTypes from 'prop-types';
import AddIcon from '@material-ui/icons/Add';
import Fab from '@material-ui/core/Fab';
import Tooltip from '@material-ui/core/Tooltip';
import { withStyles} from "@material-ui/core";
import AddEditorialDialog from "../components/addEditorial/AddEditorialDialog";
import Firestore from "../api/FirestoreDb";
const styles  = theme => ({
	fab: {
		margin: theme.spacing(2),
	},
	absolute: {
		position: 'absolute',
		bottom: theme.spacing(2),
		right: theme.spacing(3),
	},
});

class EditorialList extends Component{
	constructor(props){
		super(props);
		this.state = {
			result:'',
			message: '',
			open: false
		}
		this.handleClick = this.handleClick.bind(this);
	}
	async componentDidMount() {
		try {
			const querySnapshot = await Firestore.getEditorials();
			querySnapshot.forEach(function(doc) {
				//console.log(doc.id, " => ", doc.data());
			});

		}
		catch (e) {
			console.log(e);
		}
	}
	handleClick(){
		this.setState({
			open: !this.state.open
		})
	}
	render(){
		const { classes } = this.props;
		return (
			<div>
				<AddEditorialDialog
					open={this.state.open}
					onClose={this.handleClick}
				/>
				<Tooltip
					title="Add"
					aria-label="add"
				>
					<Fab color="primary"
						 className={classes.absolute}
						 onClick={this.handleClick}
					>
						<AddIcon />
					</Fab>
				</Tooltip>
			</div>
		);
	}
}
EditorialList.prototypes = {
	classes: PropTypes.object.isRequired,
}
export default withStyles(styles)(EditorialList);