import React, { useEffect, useState }  from "react";

import styled ,{css} from "styled-components";
import { useParams } from 'react-router-dom';

import comment from "../../JSON/productcomment"

function CommentList ({item}){
    return(
        <>
        {item ?
        <>
        <CommentBox >
        <SpanFont Mid> {item.mid} </SpanFont>
        <Button> ❤ </Button> 0 <Button> 🤍 </Button>
        <br />
        <PFont Rtext> {item.rtext} </PFont>
        <SpanFont Rgrade> {item.rgrade} / 5 </SpanFont>

        </CommentBox>
        </>
        :
        <>
        </>
        }
        </>
    )
}

const CommentBox = styled.div`
margin : 1% 5% 1%;
padding : 3%;
border : 0px solid #000000;
border-radius : 20px;
background: #f3f3f3;
`

const SpanFont = styled.span`
font-family : 'tway';

${(props) =>
    props.Mid &&
    css`
    font-size : 1.5rem;
`}
${(props) =>
    props.Rgrade &&
    css`
    font-size : 1.2rem;
`}
`

const PFont = styled.p`
    font-family : 'tway';
    font-size : 1.2rem;

`

const Button = styled.button`
border : 0px;
background: transparent;
`

export default function ItemComment ({item}){
    
    const { pid } = useParams();

    const [arr,setArr] = useState([])

    const [Rating,setRating] = useState(0)

    useEffect(()=>{
        setArr(comment.Comment.filter(item => pid === item.pid ))
    },[pid,item.pid])
    
    const reducer = (acc,curr) => acc + curr

    useEffect(()=>{
    let grade = (arr.map((item) => Number(item.rgrade)))

    if(grade.length >= 1){
        setRating(grade.reduce(reducer) / arr.length)
    }
    },[arr])

    return(
        <Positioner id='2'>
            <Title> 상품 후기  </Title>
            <State> {Rating} / 5 ({arr.length}) </State>
            {arr.map((i, index) =>
            <CommentList item={i} key={index} /> )}
        </Positioner>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;

background: #FFFFFF;

height : fit-content;

padding : 0px;
border: 0px;

padding-bottom : 5rem;

`

const Title = styled.p`
font-family : 'tway';
font-size : 2rem;

text-align : center;
`

const State = styled.p`
font-family : 'tway';
font-size : 1rem;

text-align : center;
`