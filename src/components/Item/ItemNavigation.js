import React from "react";
import styled ,{css} from "styled-components";

export default function ItemNavigation({ScrollActiveNavigator}){
    return(
        <Positioner>
            <Navigator className={ScrollActiveNavigator ? 'flexible' : null}>
                <Button Info> 상품 설명 </Button> <Button Comment> 상품 후기 </Button>
            </Navigator>

        </Positioner>
    )
}

const Positioner = styled.div`
display: block;
flex-direction: column;
position: absolute;
top: 750px;

left : calc(50vw - 500px );
width: calc(100vw - (50vw - 500px) * 2 );

height: 600px;

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
`

const Navigator = styled.div`
width: 100%;
background: #e2e2e2;

text-align: center;

border-top: 1px solid #000000;

&.flexible{
width: calc(100vw - (50vw - 500px) * 2 );

position: sticky;
top:90px;

border-top: 0px;



}

`

const Button = styled.button`

border: 0px 1px 1px 1px solid #000000;

${(props) =>
    props.Info &&
    css`
    width: 150px;
    padding: 10px;
    background : #ffffff;
    color : #000000;

`}

${(props) =>
    props.Comment &&
    css`
    width: 150px;
    padding: 10px;
    background : #ffffff;
    color : #000000;
`}
`