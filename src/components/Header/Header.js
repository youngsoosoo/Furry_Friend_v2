import React from "react";
import styled from "styled-components";

export default function Header(){
    return(
        <Positioner>
        <WhiteBackground>
            <HeaderContents>
                <Spacer/>
                <Title>Furry</Title>
                <Search>
                <Input 
                    placeholder="찾고 싶은 상품을 검색해보세요!"/>
                <IMG> 🔎 </IMG>
                </Search>

                <MY>
                    <ICON>😀</ICON>
                    <P>마이퓨리</P>
                </MY>
                <Cart>
                    <ICON>🛒</ICON>
                    <P>장바구니</P>
                </Cart>
                <Spacer/>
            </HeaderContents>
        </WhiteBackground>
    </Positioner>
    )
}


// 상단 고정, 그림자
const Positioner = styled.div`
    display: flex;
    flex-direction: column;
    position: fixed;
    top: 35px;
    left : calc(50vw - 600px);
    width: 1200px;
    `

// 흰 배경, 내용 중간 정렬
const WhiteBackground = styled.div`
    background: white;
    display: flex;
    justify-content: center;
    height: auto;

    
`

// 해더의 내용
const HeaderContents = styled.div`
    width: 1200px;
    height: 55px;
    display: flex;
    flex-direction: row;
    align-items: center;

`

// 로고
const Title = styled.div`
    font-size: 2rem;
    letter-spacing: 5px;
    font-family: 'tway';

    margin: 30px;

`

//검색창

const Search = styled.div`
margin: 30px;

position: relative;
width: 450px;

`
const Input = styled.input`

width: 400px;

height: 30px;

border : 3px solid #8ec64e;
border-radius : 24px;
outline: none;
padding-left: 10px;

`
const IMG = styled.span`
position: absolute;

top : 7px;
right : 45px;

margin: 0;
`

//장바구니
const MY = styled.button`
    margin: 5px;
    border : 0px;
    background: #ffffff;
    line-height: 0;

    overflow : hidden;
    z-index:99;
`
const Cart = styled.button`
    margin: 5px;
    
    border : 0px;
    background: #ffffff;
    line-height: 0;
`
const ICON = styled.p`
font-size : 2rem;

`
const P = styled.p`
font-weight : 1000;
`
// 중간 여백

const Spacer = styled.div`
    flex-grow: 1;
`;