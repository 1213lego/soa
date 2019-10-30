import { Link } from "react-router-dom";
import React from "react";
import { Button, makeStyles,Fab } from "@material-ui/core";
import AddIcon from '@material-ui/icons/Add';
import EditIcon from '@material-ui/icons/Edit';
import FavoriteIcon from '@material-ui/icons/Favorite';
import NavigationIcon from '@material-ui/icons/Navigation';
const Header = () => {
  const classes = useStyles();
  return (
    <nav>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/about">About</Link>
        </li>
        <li>
          <Button
            variant="contained"
            color="primary"
            className={classes.button}
          >
            Hello World
          </Button>
        </li>
        <li>
          <Button
            variant="contained"
            color="secondary"
            className={classes.button}
          >
            Secondary
          </Button>
        </li>
        <li>
          <div>
            <div>
              <Fab color="primary" aria-label="add" className={classes.fab}>
                <AddIcon />
              </Fab>
              <Fab color="secondary" aria-label="edit" className={classes.fab}>
                <EditIcon />
              </Fab>
              <Fab variant="extended" aria-label="like" className={classes.fab}>
                <NavigationIcon className={classes.extendedIcon} />
                Extended
              </Fab>
              <Fab disabled aria-label="like" className={classes.fab}>
                <FavoriteIcon />
              </Fab>
            </div>
          </div>
        </li>
      </ul>
    </nav>
  );
};
const useStyles = makeStyles(theme => ({
  button: {
    margin: theme.spacing(1)
  },
  input: {
    display: "none"
  },
  fab: {
    margin: theme.spacing(1)
  },
  extendedIcon: {
    marginRight: theme.spacing(1)
  }
}));
export default Header;
