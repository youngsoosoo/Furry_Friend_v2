import React from 'react';

import styled from 'styled-components';
import { useParams } from 'react-router-dom';

import product from '../JSON/product.json'

/* Header import */
import Header from '../components/Header/Header';

/* Top import */
import Top from '../components/Top/Top';

/* Item import */
import Item from '../components/Item/Item';

/* ItemNavigation import */
import ItemNavigation from '../components/Item/ItemNavigation';

export default function ItemDetail({ScrollActive , ScrollActiveNavigator}){

    const { pid } = useParams();

    //Item 컴포넌트에 넘겨줄 props 데이터.
    let item = product.product[pid]
    
    return (
        <>
            <Container>
                <Top ScrollActive={ScrollActive} /> 
                <Header ScrollActive={ScrollActive}/>
                <Item item={item} />
                <ItemNavigation item={item} ScrollActiveNavigator={ScrollActiveNavigator}/>

            </Container>

        </>
    )
}

const Container = styled.div`


padding: 0px;
border: 0px;
margin: 0px;

width : fit-content;
height: 200%;

overflow-x : hidden;
`