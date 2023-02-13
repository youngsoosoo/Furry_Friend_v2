import React ,{useEffect, useState} from "react";
import styled from "styled-components";

import Back from "../Back/Back";
import productImg from '../../JSON/productImg.json'


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
                    
                <Li> <Name> {item.pname} </Name> </Li>
                <Li> <SpanFont> 가격 : {item.pprice} </SpanFont> </Li>                
                <Li> <SpanFont> 수량 :  </SpanFont>
                <IncreaseButton onClick={()=>increase()}>
                    +
                </IncreaseButton>
                <SpanFont> {amount} </SpanFont>
                <DecreaseButton onClick={()=>decrease()}>
                    -
                </DecreaseButton> </Li>

                <Li> <SpanFont> 결제 가격 : {totalPrice} </SpanFont> </Li> 
                </Ul>

            </Positioner>

            {/* <Positioner>
            <PFont> 설명 : {item.pexplain} </PFont>
            </Positioner> */}

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

height: 600px;

padding : 0px;
padding-bottom : 3rem;

border: 0px;

background-color: #FFFFFF;

&.flexible{
}

`;

const Img = styled.img`
width: 500px;
height: 512px;


margin-left : 5vw;
position : absolute;
left : 5%;
top : 10%;

`

const Ul = styled.ul`
display: inline-block;
position : absolute;
right : 15%;
top : 10%;

list-style : none;

`

const Li = styled.li`

margin-bottom : 50px;
padding-bottom : 15px;
border-bottom : 1px solid #d3c9d2;

`

const Name = styled.span`
font-family : 'tway';

font-size : 1.5rem;

`
const SpanFont = styled.span`
font-family : 'tway';
font-size : 1.5rem;

`

const PFont = styled.p`
font-family : 'tway';
font-size : 1.5rem;

`

const Button = styled.button`

`

const IncreaseButton = styled(Button)`
`

const DecreaseButton = styled(Button)`
`