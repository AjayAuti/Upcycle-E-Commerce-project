import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';
const Search = () => {
  const [keyword, setKeyword] = useState('');
  let history = useHistory();
  const searchHandler = (e) => {
    e.preventDefault();
    if (keyword.trim()) {
      history.push(`/search/${keyword}`)
    }
    else
    history.push('/')
  };
  return (
    <div>
      <Form className='d-flex mx-2' onSubmit={searchHandler}>
        <Form.Control
          type='search'
          placeholder='Search'
          className='me-3'
          aria-label='Search'
          onChange={(e) => setKeyword(e.target.value)}
        />
        <Button type='submit' className='text-color:black bg-dark'>
          Search
        </Button>
      </Form>
    </div>
  );
};

export default Search;
