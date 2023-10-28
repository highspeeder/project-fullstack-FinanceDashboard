import BoxHeader from "@/components/BoxHeader";
import DashboardBox from "@/components/DashboardBox";
import FlexBetween from "@/components/FlexBetween";
import { useGetKpisQuery, useGetProductsQuery } from "@/state/api";
import { Box, Typography, useTheme } from "@mui/material";
import { useMemo } from "react";
import {
  CartesianGrid,
  Cell,
  Line,
  LineChart,
  Pie,
  PieChart,
  ResponsiveContainer,
  Scatter,
  ScatterChart,
  Tooltip,
  XAxis,
  YAxis,
  ZAxis,
} from "recharts";

const pieData = [
  { name: "GroupA", value: 600 },
  { name: "GroupB", value: 400 },
];

const Row2 = () => {
  const { data: operationalData } = useGetKpisQuery();
  const { data: productData } = useGetProductsQuery();
  const { palette } = useTheme();
  const pieColor = [palette.primary[800], palette.primary[300]];

  const operationalExpenses = useMemo(() => {
    return (
      operationalData &&
      operationalData[0].monthData.map(
        ({ month, operationalExpenses, nonOperationalExpenses }) => {
          return {
            name: month.substring(0, 3),
            "Operational Expenses": operationalExpenses,
            "Non Operational Expenses": nonOperationalExpenses,
          };
        }
      )
    );
  }, [operationalData]);

  const productExpenseData = useMemo(() => {
    return (
      productData &&
      productData.map(({ id, price, expense }) => {
        return {
          id: id,
          price: price,
          expense: expense,
        };
      })
    );
  }, [productData]);

  return (
    <>
      <DashboardBox gridArea='d' style={{ overflow: "hidden" }}>
        <BoxHeader title='운영 비용 vs 비운영 비용' sideText='+4%' />
        <ResponsiveContainer width='100%' height='100%'>
          <LineChart
            data={operationalExpenses}
            margin={{
              top: 20,
              right: 0,
              left: -10,
              bottom: 55,
            }}
          >
            <CartesianGrid
              vertical={false}
              stroke={palette.grey[800]}
            ></CartesianGrid>
            <XAxis
              dataKey='name'
              tickLine={false}
              style={{ fontSize: "10px" }}
            />
            <YAxis
              yAxisId='left'
              orientation='left'
              tickLine={false}
              axisLine={false}
              style={{ fontSize: "10px" }}
            />
            <YAxis
              yAxisId='right'
              orientation='right'
              tickLine={false}
              axisLine={false}
              style={{ fontSize: "10px" }}
            />
            <Tooltip />
            <Line
              yAxisId='left'
              type='monotone'
              dataKey='Non Operational Expenses'
              name='비운영 비용'
              stroke={palette.tertiary[500]}
            />
            <Line
              yAxisId='right'
              type='monotone'
              dataKey='Operational Expenses'
              name='운영비용'
              stroke={palette.primary.main}
            />
          </LineChart>
        </ResponsiveContainer>
      </DashboardBox>
      <DashboardBox gridArea='e' style={{ overflow: "hidden" }}>
        <BoxHeader title='활동 및 대상' sideText='+4%' />
        <FlexBetween mt='0.25rem' gap='1.5rem' pr='1rem'>
          <PieChart
            width={110}
            height={100}
            margin={{
              top: 0,
              right: -10,
              left: 10,
              bottom: 0,
            }}
          >
            <Pie
              stroke='none'
              data={pieData}
              innerRadius={18}
              outerRadius={38}
              paddingAngle={2}
              dataKey='value'
            >
              {pieData.map((_, index) => (
                <Cell key={`cell-${index}`} fill={pieColor[index]} />
              ))}
            </Pie>
          </PieChart>
          <Box ml='-0.7rem' flexBasis='40%' textAlign='center'>
            <Typography variant='h5'>목표 판매</Typography>
            <Typography m='0.3rem 0' variant='h3' color={palette.primary[300]}>
              83
            </Typography>
            <Typography variant='h6'>활동으로 원하는 재정적 목표</Typography>
          </Box>
          <Box flexBasis='40%'>
            <Typography variant='h5'>매출 손실</Typography>
            <Typography variant='h6'>손실 25% 감소</Typography>
            <Typography mt='0.4rem' variant='h5'>
              수익 마진
            </Typography>
            <Typography variant='h6'>
              마진은 지난달보다 30% 증가했습니다.
            </Typography>
          </Box>
        </FlexBetween>
      </DashboardBox>
      <DashboardBox gridArea='f' style={{ overflow: "hidden" }}>
        <BoxHeader title='가격 대비 비용' sideText='4%' />
        <ResponsiveContainer width='100%' height='100%'>
          <ScatterChart
            margin={{
              top: 20,
              right: 25,
              bottom: 40,
              left: -10,
            }}
          >
            <CartesianGrid stroke={palette.grey[800]} />
            <XAxis
              type='number'
              dataKey='price'
              name='가격'
              axisLine={false}
              tickLine={false}
              style={{ fontSize: "10px" }}
              tickFormatter={(v) => `$${v}`}
            />
            <YAxis
              type='number'
              dataKey='expense'
              name='비용'
              axisLine={false}
              tickLine={false}
              style={{ fontSize: "10px" }}
              tickFormatter={(v) => `$${v}`}
            />
            <ZAxis type='number' range={[20]} />
            <Tooltip formatter={(v) => `$${v}`} />
            <Scatter
              name='제품 비용 비율'
              data={productExpenseData}
              fill={palette.tertiary[500]}
            />
          </ScatterChart>
        </ResponsiveContainer>
      </DashboardBox>
    </>
  );
};

export default Row2;
