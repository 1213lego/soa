import React, { Component } from "react";
import PropTypes from "prop-types";
import AddIcon from "@material-ui/icons/Add";
import Fab from "@material-ui/core/Fab";
import Tooltip from "@material-ui/core/Tooltip";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  withStyles,
  Typography
} from "@material-ui/core";
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import AddEditorialDialog from "../components/addEditorial/AddEditorialDialog";
import Firestore from "../api/FirestoreDb";
const styles = theme => ({
  a: {
    display: "flex",
    justifyContent: "center"
  },
  fab: {
    margin: theme.spacing(2)
  },
  absolute: {
    position: "absolute",
    bottom: theme.spacing(2),
    right: theme.spacing(3)
  }
});

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
      let editorials = [];
      querySnapshot.forEach(function(doc) {
		editorials.push(doc.data());
		console.log(doc.data().foundationDate)
      });
      this.setState({ editorials: [...editorials] });
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
        <Typography variant="h4" className={classes.a}>
          Publishers details
        </Typography>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Nit</TableCell>
              <TableCell align="right">Name</TableCell>
              <TableCell align="right">Country</TableCell>
              <TableCell align="right">Phone</TableCell>
              <TableCell align="right">Foundation date</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.editorials.map(editorial => (
              <TableRow key={editorial.nit}>
                <TableCell component="th" scope="row">
                  {editorial.nit}
                </TableCell>
                <TableCell align="right">{editorial.name}</TableCell>
                <TableCell align="right">{editorial.country}</TableCell>
                <TableCell align="right">{editorial.phone}</TableCell>
                <TableCell align="right">
                  {editorial.foundationDate.toDate().toDateString()}
                </TableCell>
				<TableCell align="right">
					<EditIcon/>
				</TableCell>
				<TableCell align="right">
					<DeleteIcon/>
				</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <AddEditorialDialog open={this.state.open} onClose={this.handleClick} />
        <Tooltip title="Add" aria-label="add">
          <Fab
            color="primary"
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
  classes: PropTypes.object.isRequired
};
export default withStyles(styles)(EditorialList);
