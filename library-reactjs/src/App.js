import React from 'react';
import './App.css';
import About from './pages/About';
import Home from './pages/Home';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import ResponsiveDrawer from './components/header/NavBar';
function App() {
	return (
			<Router>
				<ResponsiveDrawer>
					<Switch>
						<Route path="/about">
							<About />
						</Route>
						<Route path="/">
							<Home />
						</Route>
					</Switch>
				</ResponsiveDrawer>
			</Router>
	);
}
export default App;
