import React, { useState } from 'react';
import Pagination from 'react-js-pagination';

import '../../styles/Pagination.css'

const Paging = ({ page, count, setPage }) => {
    return (
      <Pagination
        activePage={page}
        itemsCountPerPage={7}
        totalItemsCount={count}
        pageRangeDisplayed={5}
        prevPageText={'‹'}
        nextPageText={'›'}
        onChange={setPage}
      />
    );
  };

  export default Paging