import React from 'react';

import styled from 'styled-components';

/* Header import */
import Header from '../components/Header/Header';

/* Top import */
import Top from '../components/Top/Top';

/* Nav import */
import Nav from '../components/Nav/Nav';

/* Menu import */
import Menu from '../components/Menu/Menu';

/* Footer import */
import Footer from '../components/Footer/Footer';

export default function Home({pcategory , clickPcategory , ScrollActive , categoryNavigation}){


    return(
    <>
        <Container>
        <Top ScrollActive={ScrollActive} /> 
        <Header ScrollActive={ScrollActive}/> 

        <Nav pcategory={pcategory} clickPcategory={clickPcategory} />
        <Menu pcategory={pcategory} ScrollActive={ScrollActive}  categoryNavigation={categoryNavigation} />
        
        </Container>
        
        <Footer />
    </>
    )
}

const Container = styled.div`

position: relative;
padding: 0px;
border: 0px;

width : 100vw;
height: 200%;

`