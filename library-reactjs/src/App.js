import React from 'react';
import './App.css';
import About from './pages/About';
import Home from './pages/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import ResponsiveDrawer from './components/header/NavBar';
function App() {
	return (
		<Router>
			<Switch>
				<ResponsiveDrawer>
					<Route path="/about">
						<About />
					</Route>
					<Route exact path="/">
						<Home />
					</Route>
				</ResponsiveDrawer>
			</Switch>
		</Router>
	);
}
export default App;
