import React ,{useEffect, useState} from "react";
import styled from "styled-components";

import Back from "../Back/Back";
import productImg from '../../JSON/productImg.json'


export default function Item({item}){

    console.log(item.pid)
    console.log(item.pcategory)
    console.log(item.pname)
    console.log(item.pprice)
    console.log(item.pexplain)

    /*수량*/

    const [amount,setAmount] = useState(1)

    //총 가격 총 삼품가격과 헷길리지말기
    const [price,setPrice] = useState(Number(item.pprice))

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
        setPrice(price => item.pprice*amount)
    },[amount])

    console.log(amount)
    console.log(price)

    return(
        <>
            <Positioner>
                <Back />

                <Img src={productImg.Img[item.pid].src1} alt='x' />

                <Name>
                이름 : {item.pname}
                </Name>
                <Font> 가격 : {item.pprice} </Font>
                <Font> 설명 : {item.pexplain} </Font>
                <Font> 결제 가격 : {price} </Font>

                <IncreaseButton onClick={()=>increase()}>
                    +
                </IncreaseButton>

                <DecreaseButton onClick={()=>decrease()}>
                    -
                </DecreaseButton>
                
            </Positioner>
        </>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;
position: absolute;
top: 150px;

left : calc(50vw - 400px );
width: calc(100vw - (50vw - 400px) * 2 );

@media (min-width : 1200px){
    left : calc(50vw - 600px );
    width: calc(100vw - (50vw - 600px) * 2 );
}

height: fit-content;

padding : 0px;
padding-bottom : 5rem;
border: 0px;

background-color: #FFFFFF;

&.flexible{
}

`;

const Img = styled.img`
width: 260px;
height: 256px;

padding : 10px;


`

const Name = styled.span`
font-family : 'tway';
`

const Font = styled.span`
font-family : 'tway';
`

const Button = styled.button`

`

const IncreaseButton = styled(Button)`
`

const DecreaseButton = styled(Button)`
`