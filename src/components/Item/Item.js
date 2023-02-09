import React from "react";
import styled from "styled-components";

/*상품 불러오기*/
import product from '../../JSON/product.json'

function ItemList({item,pcategory}){
    
    return(
        <>
            {item.pcategory.includes(pcategory)
            ?
            <Frame>
            
            {item.pname}
            {item.pprice}
            </Frame>
            :
            <>
            </>
            }
        </>
    )
}   

export default function Item({pcategory}){

    // json길이

    return(
        <Positioner>            
            {product.product.map((item)=>
            <ItemList item={item} id={item.id} pcategory={pcategory} />)}
        </Positioner>
    )
}

const Positioner = styled.div`
    display: inline-block;
    flex-direction: column;
    position: fixed;
    top: 220px;
    left : calc(50vw - 400px );
    width: calc(100vw - (50vw - 400px) * 2 );
    height: 600px;
    z-index:99;
    
    padding : 0px;
    border: 0px;
    
    background-color: #FFFFFF;
    
`;

const Frame = styled.div`
display: inline-block;
/*부모 요소에 따라 크기 변경 => %*/
width: 30%;
height: 30%;

margin-left : 2.5%;
margin-top : 2.5%;

background : aliceblue;
`

