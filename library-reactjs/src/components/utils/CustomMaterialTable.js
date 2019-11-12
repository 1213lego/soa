import React from 'react';
import MaterialTable from 'material-table';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
const useStyles = makeStyles((theme) => ({
	close: {
		padding: theme.spacing(0.5)
	}
}));
export default function CustomMaterialTable(props) {
	const classes = useStyles();
	const [ open, setOpen ] = React.useState(false);
	const [ message, setMessage ] = React.useState('');
	const handleOpen = () => {
		setOpen(true);
	};

	const handleClose = (event, reason) => {
		if (reason === 'clickaway') {
			return;
		}

		setOpen(false);
	};
	const { columns, data, title, addRow, updateRow, deleteRow } = props;
	return (
		<React.Fragment>
			<MaterialTable
				title={title}
				columns={columns}
				data={data}
				editable={{
					onRowAdd: (newData) =>
						new Promise(async (resolve, reject) => {
							try {
								await addRow(newData);
								resolve();
							} catch (error) {
								reject(error);
							}
						}),
					onRowUpdate: (newData, oldData) =>
						new Promise(async (resolve, reject) => {
							try {
								await updateRow(newData);
								resolve();
							} catch (error) {
								reject(error);
							}
						}),
					onRowDelete: (oldData) =>
						new Promise(async (resolve, reject) => {
							try {
								await deleteRow(oldData);
							} catch (error) {
								console.log(error);
								setMessage(error.message);
								handleOpen();
							}
							resolve();
						})
				}}
			/>
			<Snackbar
				anchorOrigin={{
					vertical: 'top',
					horizontal: 'right'
				}}
				open={open}
				autoHideDuration={6000}
				onClose={handleClose}
				ContentProps={{
					'aria-describedby': 'message-id'
				}}
				message={<span id="message-id">{message}</span>}
				action={[
					<Button key="undo" color="secondary" size="small" onClick={handleClose}>
						UNDO
					</Button>,
					<IconButton
						key="close"
						aria-label="close"
						color="inherit"
						className={classes.close}
						onClick={handleClose}
					>
						<CloseIcon />
					</IconButton>
				]}
			/>
		</React.Fragment>
	);
}
