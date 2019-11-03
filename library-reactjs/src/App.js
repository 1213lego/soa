import React from 'react';
import './App.css';

import Home from './pages/Home';
import EditorialList from './pages/Publishers';
import Books from './pages/Books';
import About from './pages/About';

import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import ResponsiveDrawer from './components/header/NavBar';

function App() {
	return (
		<Router>
			<Switch>
				<ResponsiveDrawer>
					<Route exact path="/">
							<Home />
					</Route>
					<Route path="/publishers">
						<EditorialList/>
					</Route>
					<Route path="/books">
						<Books/>
					</Route>
					<Route path="/about">
						<About />
					</Route>
				</ResponsiveDrawer>
			</Switch>
		</Router>
	);
}
export default App;
