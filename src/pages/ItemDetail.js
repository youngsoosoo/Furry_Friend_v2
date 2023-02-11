import React from 'react';

import styled from 'styled-components';

/* Header import */
import Header from '../components/Header/Header';

/* Top import */
import Top from '../components/Top/Top';

/* Nav import */
import Nav from '../components/Nav/Nav';

export default function ItemDetail({ScrollActive}){
    return (
        <>
            <Top ScrollActive={ScrollActive} /> 
            <Header ScrollActive={ScrollActive}/> 

        </>
    )
}

