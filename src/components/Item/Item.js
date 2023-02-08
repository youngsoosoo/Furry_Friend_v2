import React from "react";
import styled from "styled-components";

export default function Item({pcategory}){
    console.log(pcategory)
    
    return(
        <Positioner>            
            {pcategory}
        </Positioner>
    )
}

const Positioner = styled.div`
    display: flex;
    flex-direction: column;
    position: fixed;
    top: 220px;
    left : calc(50vw - 350px);
    width: calc(100vw - (50vw - 350px) * 2 );
    height: 500px;
    z-index:99;

    background-color: #FFFFFF;

`;