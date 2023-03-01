import React from "react";

import styled from "styled-components";

export default function ItemInfo({item}){
    return(
        <Positioner id='1'>
            <Title >상품 설명</Title>      
            <Info>{item.pexplain}</Info>
        </Positioner>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;


left : calc(50vw - 500px );
width: calc(100vw - (50vw - 500px) * 2 );


padding : 0px;
border: 0px;

background-color: #FFFFFF;

`
const Title = styled.p`
font-family : 'tway';
font-size : 2rem;

text-align : center;

`

const Info = styled.p`
font-family : 'tway';
font-size : 1.2rem;

text-align : center;

`