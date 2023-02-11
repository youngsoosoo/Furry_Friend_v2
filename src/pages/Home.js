import React from 'react';

import styled from 'styled-components';

/* Header import */
import Header from '../components/Header/Header';

/* Top import */
import Top from '../components/Top/Top';

/* Nav import */
import Nav from '../components/Nav/Nav';

/* Item import */
import Item from '../components/Item/Item';

/* Footer import */
import Footer from '../components/Footer/Footer';

export default function Home({pcategory , clickPcategory , ScrollActive , categoryNavigation}){


    return(
    <>
        <Container>
        <Top ScrollActive={ScrollActive} /> 
        <Header ScrollActive={ScrollActive}/> 

        <Nav pcategory={pcategory} clickPcategory={clickPcategory} />
        <Item pcategory={pcategory} ScrollActive={ScrollActive}  categoryNavigation={categoryNavigation} />
        
        </Container>
        
        <Footer />
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