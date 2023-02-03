import axios from 'axios';
import { useEffect, useState } from 'react';
import Moment from 'react-moment';
import Dropdown from 'react-bootstrap/Dropdown';

function Orders() {
  const [orders, setOrders] = useState([]);
  const [show, setShow] = useState(false);
  const [details, setDetails] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/orders').then((resp) => {
      console.log(resp.data);
      setOrders(resp.data.data);
    });
  }, []);

  const showDetails = (orderid) => {
    axios.get('http://localhost:8080/api/orders/' + orderid).then((resp) => {
      console.log(resp.data);
      setDetails(resp.data.data.details);
    });
    setShow(true);
  };
  return (
    <div className='container-fluid'>
      <div className='row'>
        <div className='col-sm-7'>
          <h4 className='p-2 text-center text-white'>My Purchased Orders</h4>
          <table className='table table-bordered table-sm table-light table-hover table-striped'>
            <thead>
              <tr>
                <th>Id</th>
                <th>Order Date</th>
                <th>Amount</th>
                <th>Customer</th>
                <th>Action</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              {orders.map((x) => (
                <tr key={x.orderid}>
                  <td>{x.orderid}</td>
                  <td>
                    <Moment format='ddd, DD-MMM-YYYY'>{x.orderDate}</Moment>
                  </td>
                  <td>&#8377; {x.payment.amount}</td>
                  <td>{x.customer.name}</td>
                  <td>
                    <button
                      onClick={(e) => showDetails(x.orderid)}
                      className='btn btn-primary btn-sm'
                    >
                      Show Details
                    </button>
                  </td>
                  <td>
                    <Dropdown>
                      <Dropdown.Toggle variant='success' id='dropdown-basic'>
                        Order Status
                      </Dropdown.Toggle>

                      <Dropdown.Menu>
                        <Dropdown.Item href='' value='Shipped'>
                          Shipped
                        </Dropdown.Item>
                        <Dropdown.Item href='' value='Out for delivery'>
                          Out for delivery
                        </Dropdown.Item>
                        <Dropdown.Item href='' value='Delivered'>
                          Delivered
                        </Dropdown.Item>
                        <Dropdown.Item href='' value='Order Cancelled'>
                          Order cancelled
                        </Dropdown.Item>
                      </Dropdown.Menu>
                    </Dropdown>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className='col-sm-5'>
          {show ? (
            <>
              <h4 className='p-2'>Order Details</h4>
              <table className='table table-bordered table-light table-hover table-striped table-sm'>
                <thead>
                  <tr>
                    <th>Id</th>
                    <th>Product</th>
                    <th>Price</th>
                    <th>Qty</th>
                  </tr>
                </thead>
                <tbody>
                  {details.map((x) => (
                    <tr key={x.product.prodid}>
                      <td>{x.product.prodid}</td>
                      <td>
                        <img
                          className='mr-2 float-left'
                          src={
                            'http://localhost:8080/product-images/' +
                            x.product.prodid +
                            '/' +
                            x.product.photo
                          }
                          alt='preview'
                          width='100'
                        />
                        {x.product.pname}
                        <br />
                        {x.product.cat}
                      </td>
                      <td>{x.product.price}</td>
                      <td>{x.qty}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </>
          ) : (
            ''
          )}
        </div>
      </div>
    </div>
  );
}

export default Orders;
