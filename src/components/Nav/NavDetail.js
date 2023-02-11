import React from "react";
import styled from "styled-components";

/*json파일 불러오기*/
import pcategoryList from "../../JSON/pcategory.json"

function ButtonList({item,pcategory,clickPcategory}){
    
    /*item은 json데이터 */

    return(
        <>

        {pcategory.includes(item.type)
        ?
        <>
        <Button onClick={()=>{clickPcategory(item.category , item.name)}}>
            {item.name}
        </Button>
        </>
        :
        <>
        </>
        }
        
        </>
    )
}


export default function NavDetail({pcategory,clickPcategory}){
    

    return(
        <Positioner>
            <WhiteBackground>
                <NavDetailContents>
                    <Spacer />
                    {pcategoryList.pcategory.map((item)=>
                    <ButtonList item={item} id={item.id} pcategory={pcategory} clickPcategory={clickPcategory}  />)}
                </NavDetailContents>
            </WhiteBackground>
        </Positioner>
    )
}


const Positioner = styled.div`
    display: flex;
    flex-direction: column;
    position: fixed;
    top: 175px;
    left : calc(50vw - 600px);
    width: 1200px;
    height: 0px;
    z-index:99;

`;

// 회색 배경
const WhiteBackground = styled.div`
    width: 100%;
    height: 50px;
    
    flex-direction: row;
    align-items: center;    

`
// NavDetail 콘텐츠
const NavDetailContents = styled.div`
    width: 1200px;
    height: 30px;
    display : inline-grid;
    flex-direction: row;
    align-items: right;

    grid-template-columns: 220px repeat(10,100px);


`

const Button = styled.button`
width: 90px;
height: 40px;

border-radius: 20px;
box-shadow: 1px 1px 2px 1px rgba(0, 0, 0, 0.1);

border: 0px;
background-color: #FFFFFF;

font-family : 'tway';

`

const Spacer = styled.div`
width: 300px;
`