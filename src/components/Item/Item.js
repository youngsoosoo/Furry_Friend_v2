import React ,{useEffect, useState} from "react";
import styled ,{css} from "styled-components";

import Back from "../Back/Back";
import productImg from '../../JSON/productImg.json'
import { Navigate } from "react-router-dom";


export default function Item({item}){

    /*수량*/

    const [amount,setAmount] = useState(1)
    const [price,setPrice] = useState(Number(item.pprice))

    //총 가격 총 삼품가격과 헷길리지말기
    const [totalPrice,setTotalPrice] = useState(Number(price))

    const increase = () =>{
        setAmount(amount => amount + 1)
    }

    const decrease = () =>{
        if (amount > 1){
        setAmount(amount => amount - 1)
        } else {
            alert('최소 수량입니다.')
        }
    }

    useEffect(()=>{
        setTotalPrice(price * amount)
    },[price,amount])

    return(
        <>
            <Positioner>
                <Back />

                <Img src={productImg.Img[item.pid].src1} alt='x' />
                <Ul>
                    
                <Li NoBorder> <Name> {item.pname} </Name> </Li>
                <Li NoBorder> <SpanFont> ♡ 0개 </SpanFont> </Li>
                <Li NoBorder> <SpanFont> {item.pprice} 원 </SpanFont> </Li>                
                <Li> <SpanFont> 수량 :  </SpanFont>
                <IncreaseButton onClick={()=>increase()}>
                    +
                </IncreaseButton>
                <SpanFont> {amount} </SpanFont>
                <DecreaseButton onClick={()=>decrease()}>
                    -
                </DecreaseButton> 
                </Li>

                <Li > <SpanFont> 결제 가격 : {totalPrice} </SpanFont> </Li> 

                <Li> <Button Cart> 장바구니 </Button> <Button Buy> 바로구매 </Button></Li>
                </Ul>

            </Positioner>





        </>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;
position: absolute;
top: 150px;

left : calc(50vw - 500px );
width: calc(100vw - (50vw - 500px) * 2 );

height: 500px;

padding : 0px;
padding-bottom : 3rem;

border: 0px;

background-color: #FFFFFF;

${(props) =>
    props.Info &&
    css`
    top: 800px;


`}

${(props) =>
    props.Comment &&
    css`    


`}
`;

const Img = styled.img`
width: 40%;
height: 60%;


margin-left : 5vw;
position : absolute;
left : 5%;
top : 10%;

`

const Ul = styled.ul`
display: inline-block;
position : absolute;
right : 5%;
top : 5%;

list-style : none;

`

const Li = styled.li`

margin-top : 5%;
padding-top : 10%;
border-top : 1px solid #d3c9d2;

${(props) =>
    props.NoBorder &&
    css`
    border: 0px;
    margin-top : 0px;

`}


`

const Name = styled.span`
font-family : 'tway';

font-size : 1.5rem;

`
const SpanFont = styled.span`
font-family : 'tway';
font-size : 1.4rem;

`

const PFont = styled.p`
font-family : 'tway';
font-size : 1.5rem;

`

const Button = styled.button`
${(props) =>
    props.Cart &&
    css`
    width: 150px;
    padding: 10px;
    background : #ffffff;
    color : #000000;

`}

${(props) =>
    props.Buy &&
    css`
    width: 150px;
    padding: 10px;
    background : #000000;
    color : #ffffff;
`}

`

const IncreaseButton = styled(Button)`
`

const DecreaseButton = styled(Button)`
`