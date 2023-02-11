import React from 'react';

import styled from 'styled-components';

/* Header import */
import Header from '../components/Header/Header';

/* Top import */
import Top from '../components/Top/Top';

export default function ItemDetail({ScrollActive}){
    return (
        <>
            <Container>
                <Top ScrollActive={ScrollActive} /> 
                <Header ScrollActive={ScrollActive}/> 
            </Container>
        </>
    )
}

const Container = styled.div`

position: relative;

padding: 0px;
border: 0px;

width : 100%;
height: 200%;
`