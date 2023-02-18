import React ,{useState}from 'react';

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

/* ItemNInfo import */
import ItemInfo from '../components/Item/ItemInfo';

/* ItemComment import */
import ItemComment from '../components/Item/ItemComment';

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
                <Wrapper>
                    <ItemInfo  item={item} />
                    <ItemComment  item={item} />
                </Wrapper>
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
const Wrapper = styled.div`
padding: 0px;
border: 0px;
margin: 0px;
display: block;
width : fit-content;
height: fit-content;

position: absolute;
top:700px;

left : calc(50vw - 500px );
`