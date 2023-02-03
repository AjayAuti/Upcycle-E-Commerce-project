import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { Route } from 'react-router-dom';
import RoleNavbar from './RoleNavbar';
import logo from './logo.jpg';
import Search from './Search';
import { Form, Button } from 'react-bootstrap';

const { Fragment } = require('react');

function NavBar() {
  const state = useSelector((state) => state);
  console.log('LoggedIn ', state.loggedin);
  console.log('Cart ', state.cart);
  return (
    <Fragment>
      <div className='clearfix'></div>
      <nav
        className='navbar navbar-expand-lg navbar-dark bg-primary position-sticky'
        style={{ top: 0, zIndex: '1000' }}
      >
        <Link className='navbar-brand' to='/'>
          <img
            src={logo}
            className='App-logo'
            alt='logo'
            height={55}
            width={180}
          />
        </Link>
        <button
          className='navbar-toggler'
          type='button'
          data-toggle='collapse'
          data-target='#navbarNavDropdown'
          aria-controls='navbarNavDropdown'
          aria-expanded='false'
          aria-label='Toggle navigation'
        >
          <span className='navbar-toggler-icon'></span>
        </button>
        <div className='collapse navbar-collapse' id='navbarNavDropdown'>
          <ul className='navbar-nav'>
            <li className='nav-item active'>
              <Link className='nav-link' to='/'>
                Home
              </Link>
            </li>
            <li className='nav-item dropdown'>
              <Link
                className='nav-link dropdown-toggle'
                to='#'
                id='navbarDropdownMenuLink'
                role='button'
                data-toggle='dropdown'
                aria-haspopup='true'
                aria-expanded='false'
              >
                Categories
              </Link>
              <div
                className='dropdown-menu'
                aria-labelledby='navbarDropdownMenuLink'
              >
                <Link
                  className='dropdown-item'
                  to='/api/products?cats=Home Utility'
                >
                  Home Utility
                </Link>
                <Link className='dropdown-item' to='/cats?cat=Furniture'>
                  Furniture
                </Link>
                <Link className='dropdown-item' to='/cats?cat=Electronics'>
                  Electronics
                </Link>
                <Link className='dropdown-item' to='/cats?cat=Living & Decor'>
                  Living & Decor
                </Link>
                <Link
                  className='dropdown-item'
                  to='/cats?cat=Kitchen Essenetials'
                >
                  Kitchen Essenetials
                </Link>
                {/* <Link className="dropdown-item" to="/cats?cat=Washing Machines">Washing Machines</Link>                         */}
              </div>
            </li>
          </ul>
          <div className='col-12 col-md-6 mt-2 mt-md-0'>
            <Route render={({ history }) => <Search history={history} />} />
          </div>
          <RoleNavbar isLoggedIn={state.loggedin.IsLoggedIn} />
        </div>
      </nav>
    </Fragment>
  );
}

export default NavBar;
