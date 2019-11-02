import React, { Fragment, useState } from 'react';
import { Link } from 'react-router-dom';
import {
	MoveToInbox as InboxIcon,
	Menu as MenuIcon,
	Home as HomeIcon,
	LocalLibrary,
	LibraryAdd
} from '@material-ui/icons';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import {
	List,
	ListItem,
	Typography,
	AppBar,
	CssBaseline,
	Divider,
	Drawer,
	Hidden,
	IconButton,
	ListItemIcon,
	ListItemText,
	Toolbar
} from '@material-ui/core';

const drawerWidth = 230;

const useStyles = makeStyles((theme) => ({
	root: {
		display: 'flex'
	},
	drawer: {
		[theme.breakpoints.up('sm')]: {
			width: drawerWidth,
			flexShrink: 0
		}
	},
	appBar: {
		zIndex: theme.zIndex.drawer + 1
	},
	menuButton: {
		marginRight: theme.spacing(2),
		[theme.breakpoints.up('sm')]: {
			display: 'none'
		}
	},
	toolbar: theme.mixins.toolbar,
	drawerPaper: {
		width: drawerWidth
	},
	content: {
		flexGrow: 1,
		padding: theme.spacing(3)
	}
}));

function ResponsiveDrawer(props) {
	const { container, children } = props;
	const { pathname } = props.location;
	const classes = useStyles();
	const theme = useTheme();
	const [ mobileOpen, setMobileOpen ] = useState(false);

	const handleDrawerToggle = () => {
		setMobileOpen(!mobileOpen);
	};

	const drawer = (
		<div>
			<div className={classes.toolbar} />
			<Divider />
			<List>
				<ListItem component={Link} to="/" selected={'/' === pathname}>
					<ListItemIcon>
						<HomeIcon />
					</ListItemIcon>
					<ListItemText primary="Home" />
				</ListItem>
				<ListItem component={Link} to="/editorials" selected={'/editorials' === pathname}>
					<ListItemIcon>
						<LocalLibrary />
					</ListItemIcon>
					<ListItemText primary="Editorials" />
				</ListItem>
				<ListItem component={Link} to="/add-editorial" selected={'/add-editorial' === pathname}>
					<ListItemIcon>
						<LibraryAdd />
					</ListItemIcon>
					<ListItemText primary="Add Editorial" />
				</ListItem>
			</List>
			<Divider />
			<List>
				<ListItem component={Link} to="/about" selected={'/about' === pathname}>
					<ListItemIcon>
						<InboxIcon />
					</ListItemIcon>
					<ListItemText primary="About" />
				</ListItem>
			</List>
		</div>
	);

	return (
		<Fragment>
			<CssBaseline />
			<div className={classes.root}>
				<AppBar position="fixed" className={classes.appBar}>
					<Toolbar>
						<IconButton
							color="inherit"
							aria-label="open drawer"
							edge="start"
							onClick={handleDrawerToggle}
							className={classes.menuButton}
						>
							<MenuIcon />
						</IconButton>
						<Typography variant="h6" noWrap>
							ReactJs + Firestore App
						</Typography>
					</Toolbar>
				</AppBar>
				<nav className={classes.drawer} aria-label="mailbox folders">
					{/* The implementation can be swapped with js to avoid SEO duplication of links. */}
					<Hidden smUp implementation="css">
						<Drawer
							container={container}
							variant="temporary"
							anchor={theme.direction === 'rtl' ? 'right' : 'left'}
							open={mobileOpen}
							onClose={handleDrawerToggle}
							classes={{
								paper: classes.drawerPaper
							}}
							ModalProps={{
								keepMounted: true // Better open performance on mobile.
							}}
						>
							{drawer}
						</Drawer>
					</Hidden>
					<Hidden xsDown implementation="css">
						<Drawer
							classes={{
								paper: classes.drawerPaper
							}}
							variant="permanent"
							open
						>
							{drawer}
						</Drawer>
					</Hidden>
				</nav>
				<main className={classes.content}>
					<div className={classes.toolbar} />
					{children}
				</main>
			</div>
		</Fragment>
	);
}
export default ResponsiveDrawer;
