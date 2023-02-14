import React from "react";

import styled from "styled-components";

function CommentList ({item}){
    return(
        {}
    )
}


export default function ItemComment ({item}){
    return(
        <Positioner>
            {item.pid}
        </Positioner>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;


left : calc(50vw - 500px );
width: calc(100vw - (50vw - 500px) * 2 );

height: fit-content;

padding : 0px;
border: 0px;

background-color: #e2e2e2;

`