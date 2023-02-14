import React from "react";

import styled from "styled-components";

export default function ItemInfo({item}){
    return(
        <Positioner>
            <p>{item.pexplain}</p>



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