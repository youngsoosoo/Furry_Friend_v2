import React from "react";
import styled from "styled-components";
import {Link} from 'react-router-dom'

export default function Header({ScrollActive}){
    return(
        <Positioner className={ScrollActive ? 'flexible' : null}>
        <WhiteBackground>
            <HeaderContents>
                <Spacer/>
                <StyledLink to='/'>
                <Title>Furry</Title>
                </StyledLink>
                <Search>
                <Input 
                    placeholder="찾고 싶은 상품을 검색해보세요!"/>
                <IMG> 🔎 </IMG>
                </Search>

                <MY className={ScrollActive ? 'flexible' : null}>
                    <ICON>😀</ICON>
                    <P>마이퓨리</P>
                </MY>
                <Cart className={ScrollActive ? 'flexible' : null}>
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
    position: absolute;

    display: flex;
    flex-direction: column;
    top: 40px;

    width: 100vw;
    height: 60px;
    
    padding : 0px;
    border: 0px;
    
    margin-left: -10px;

    z-index:99;

    background : #FFFFFF;


    &.flexible{
    
    position: fixed;
    background : #FFFFFF;
    height: 60px;
    margin-top: -10px;
    }
    `

// 흰 배경, 내용 중간 정렬
const WhiteBackground = styled.div`
    
    display: flex;
    justify-content: center;
    height: auto;
`

// 해더의 내용
const HeaderContents = styled.div`
    width: 1200px;
    height: 60px;
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
//StyledLink
const StyledLink = styled(Link)`
text-decoration : none;
&:focus, &:hover, &:visited, &:link, &:active {
        text-decoration: none;
        color : #000000;
    }


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
border-radius : 20px;
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

    border : 0px;
    padding: 0px;
    line-height: 0;

    background: transparent;

`
const Cart = styled.button`
    margin-left : 10px;
    border : 0px;
    padding: 0px;
    line-height: 0;

    background: transparent;
    


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