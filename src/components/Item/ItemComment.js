import React  from "react";

import styled ,{css} from "styled-components";
import { useParams } from 'react-router-dom';

import comment from "../../JSON/productcomment"

function CommentList ({item}){
    const { pid } = useParams();

    return(
        <>
        {item.pid === pid ?
        <>
        <CommentBox id='2'>
        <SpanFont Mid> {item.mid} </SpanFont>
        <SpanFont Rgrade> {item.rgrade} </SpanFont>
        <Button> ❤ </Button> 0 <Button> 🤍 </Button>
        <br />
        <PFont Rtext> {item.rtext} </PFont>
        
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

    return(
        <Positioner>
            <Title> 상품 후기 </Title>
            {comment.Comment.map((i, index) =>
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

