import React ,{useState} from 'react'
import styled ,{css} from 'styled-components';

export default function Top({ScrollActive}){
    const [login,setLogin] = useState(false)


    return(
        <Positioner className={ScrollActive ? 'flexible' : null}>
            <GreyBackground>
            <TopContents>
                <Spacer />
                <P>홈</P>
                <Spacer />

                {login === false ? 
                <>
                <P>회원가입</P>
                <P>로그인</P>
                </>
                :
                // 로그인 했을 때
                <>
                <P></P>
                <P>로그아웃</P>
                </>}
                
            </TopContents>
            </GreyBackground>
        </Positioner>
    )
}

// 상단 고정
const Positioner = styled.div`

    display: flex;
    flex-direction: column;
    position: absolute;
    top: 0px;
    left : calc(50vw - 600px);
    width: 1200px;
    z-index:99;

    padding : 0px;
    border: 0px;    

    &.flexible{

        position: fixed;
        background : #e2e2e2;
        height: 40px;

    }
`;



// 회색 배경
const GreyBackground = styled.div`

    flex-direction: row;
    align-items: center;

    
`

const TopContents = styled.div`
    width: 1200px;
    height: 30px;
    display : inline-grid;
    flex-direction: row;
    align-items: right;

    grid-template-columns: 220px 50px 600px 90px 100px;
`

//글씨
const P = styled.span`
margin: 5px;
font-family : 'tway';

`

//여백
const Spacer = styled.div`
`